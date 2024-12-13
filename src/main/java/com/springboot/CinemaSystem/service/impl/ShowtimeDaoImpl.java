package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.ShowtimeDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ShowtimeDaoImpl implements ShowtimeDao {
	@Autowired
	private ShowtimeRepository showtimeRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private BasePriceRepository basePriceRepository;

	@Autowired
	private DayOfWeekRepository dayOfWeekRepository;

	@Autowired
	private TimeFrameRepository timeFrameRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Override
	@Scheduled(fixedRate = 60000 * 30)
//	@Scheduled(cron = "0 0 * * * *")	// chạy mỗi giờ
//	@Scheduled(cron = "*/5 * * * * *")
	@Transactional
	public void updateShowtimeAction() {
		LocalDate localDate = LocalDate.now();
		Date currentDate = Date.valueOf(localDate);
		LocalTime localTime = LocalTime.now();
		Time currentTime = Time.valueOf(localTime);
		try {
			showtimeRepository.updateActionToRunning(currentDate, currentTime);
			showtimeRepository.updateActionToEnded(currentDate, currentTime);
			showtimeRepository.updateActionToUpcoming(currentDate, currentTime);
		} catch (Exception e) {
			throw new DataProcessingException("Error update action showtime");
		}
	}

	@Override
	public ShowtimeRoomDto getRoomByShowtime(long id) {
		Showtime showtime = showtimeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
		return showtime.toShowtimeRoomDto();
	}

	@Override
	public boolean addShowtime(Showtime showtime) {
		return false;
	}


	public boolean updateShowtime(Showtime showtime) {
		return false;
	}

	@Override
	public List<Showtime> getShowtimeByMovie(int movieID) {
		return List.of();
	}

	@Override
	public boolean updateStatusShowtime(int showtimeID) {
		return false;
	}

	@Override
	public Showtime getShowtimeByID(long showtimeID) {
		return showtimeRepository.findById(showtimeID)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
	}

	@Override
	public List<Showtime> getShowtimesByRoomID(int roomID) {
		return List.of();
	}

	@Override
	public boolean checkAvailability(int showtimeID) {
		return false;
	}

	// thêm
// Schedule a new showtime
	@Override
	public Showtime scheduleShowtime(ShowtimeRequestDto dto) {
		// Tìm rạp và phòng (theater và room)
		Theater theater = theaterRepository.findById(dto.getTheaterId())
				.orElseThrow(() -> new EntityNotFoundException("Theater not found"));
		// Tìm movie và room
		Movie movie = movieRepository.findById(dto.getMovieId())
				.orElseThrow(() -> new EntityNotFoundException("Movie not found"));
		Room room = roomRepository.findById(dto.getRoomId())
				.orElseThrow(() -> new EntityNotFoundException("Room not found"));

		// Lấy BasePrice có createdAt mới nhất
		BasePrice basePrice = basePriceRepository.findTopByOrderByCreatedAtDesc()
				.orElseThrow(() -> new EntityNotFoundException("Base price not found"));

		// Tính toán endTime bằng cách cộng thời gian bắt đầu (startTime) với độ dài phim (duration)
		LocalTime startTime = dto.getStartTime().toLocalTime();
		LocalTime endTime = startTime.plusMinutes((long) movie.getDuration());  // Thêm duration vào startTime

		// Chuyển endTime thành đối tượng Time
		Time endTimeObj = Time.valueOf(endTime);

		// Kiểm tra xung đột lịch chiếu
		if (isScheduleConflict(dto.getRoomId(), dto.getDate(), dto.getStartTime(), endTimeObj, null)) {
//			throw new IllegalArgumentException("Lịch chiếu bị trùng với lịch chiếu khác trong cùng phòng và ngày.");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lịch chiếu bị trùng với lịch chiếu khác trong cùng phòng và ngày.");

		}

		// 1. Tính toán DayOfWeek từ date
		// Chuyển java.sql.Date sang LocalDate
		LocalDate localDate = dto.getDate().toLocalDate();
		DayOfWeek dayOfWeek = dayOfWeekRepository.findByName(getDayOfWeekFromDate(localDate));
		if (dayOfWeek == null) {
			throw new EntityNotFoundException("DayOfWeek not found");
		}

		// 2. Tìm TimeFrame dựa trên startTime
		TimeFrame timeFrame = getTimeFrameFromTime(startTime);

		// 3. Tính giá vé (priceTicket) dựa trên BasePrice, DayOfWeek, và TimeFrame
		Float priceTicket = basePrice.getDefaultPrice() + dayOfWeek.getSurcharge() + timeFrame.getSurcharge();

		// Tạo đối tượng Showtime mới
		Showtime showtime = new Showtime();
		showtime.setMovie(movie);
		showtime.setRoom(room);
		showtime.setDate(dto.getDate());
		showtime.setStartTime(dto.getStartTime());
		showtime.setEndTime(endTimeObj);
		showtime.setBasePrice(basePrice);  // Thiết lập BasePrice cho Showtime
		showtime.setDayOfWeek(dayOfWeek);
		showtime.setTimeFrame(timeFrame);
		showtime.setPriceTicket(priceTicket);  // Thiết lập giá vé cho Showtime
//	showtime.setStatus(dto.isStatus());
		showtime.setAction("upcoming");

		showtime.setStatus(true);

		// Lưu Showtime vào database
		return showtimeRepository.save(showtime);
	}


	// Phương thức này để lấy tên ngày trong tuần từ LocalDate
	public String getDayOfWeekFromDate(LocalDate date) {
		// Lấy tên ngày trong tuần từ LocalDate
		String dayOfWeekName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("vi"));

		// Xử lý tên ngày để trả về chuỗi phù hợp
		switch (dayOfWeekName) {
			case "Thứ Hai":
			case "Thứ Ba":
			case "Thứ Tư":
			case "Thứ Năm":
			case "Thứ Sáu":
				return "Từ thứ 2 đến thứ 6";
			case "Thứ Bảy":
			case "Chủ Nhật":
				return "Thứ 7, Chủ nhật";
			default:
				return "Từ thứ 2 đến thứ 6"; // Mặc định cho các ngày còn lại
		}
	}

	// Phương thức tìm TimeFrame dựa trên startTime
	public TimeFrame getTimeFrameFromTime(LocalTime startTime) {
		return timeFrameRepository.findByTimeStartBeforeAndTimeEndAfter(startTime, startTime)
				.orElseThrow(() -> new EntityNotFoundException("TimeFrame not found"));
	}


	// Check for schedule conflict
	// Check for schedule conflict (hỗ trợ cả thêm và cập nhật)
	private boolean isScheduleConflict(long roomId, Date date, Time startTime, Time endTime, Long showtimeId) {
		List<Showtime> conflictingShowtimes;
		if (showtimeId != null) {
			// Loại trừ lịch chiếu hiện tại khi cập nhật
			conflictingShowtimes = showtimeRepository.findConflictingShowtimesExcludeCurrent(roomId, date, startTime, endTime, showtimeId);
		} else {
			// Kiểm tra xung đột thông thường khi thêm mới
			conflictingShowtimes = showtimeRepository.findConflictingShowtimes(roomId, date, startTime, endTime);
		}
		return !conflictingShowtimes.isEmpty();
	}


	// Update showtime status based on current time
	@Override
	public void updateShowtimeStatus() {
		Date currentDate = Date.valueOf(LocalDate.now());
		Time currentTime = Time.valueOf(LocalTime.now());
		showtimeRepository.updateActionToUpcoming(currentDate, currentTime);
		showtimeRepository.updateActionToRunning(currentDate, currentTime);
		showtimeRepository.updateActionToEnded(currentDate, currentTime);
	}

	// Toggle showtime status (active/inactive)
	public void toggleShowtimeStatus(long showtimeId) {
		Showtime showtime = showtimeRepository.findById(showtimeId)
				.orElseThrow(() -> new EntityNotFoundException("Showtime not found"));
		boolean currentStatus = showtime.isStatus();
		boolean newStatus = !currentStatus;
		showtime.setStatus(newStatus);
		showtimeRepository.save(showtime);
	}


	// Update an existing showtime
//	@Override
//	public void updateShowtime(long showtimeId, ShowtimeRequestDto showtimeRequestDto) {
//		// Tìm Showtime theo ID
//		Showtime showtime = showtimeRepository.findById(showtimeId)
//				.orElseThrow(() -> new RuntimeException("Showtime not found"));
//
//		// Tìm movie để lấy thông tin duration
//		Movie movie = movieRepository.findById(showtimeRequestDto.getMovieId())
//				.orElseThrow(() -> new RuntimeException("Movie not found"));
//
//		// Tính toán endTime bằng cách cộng thời gian bắt đầu (startTime) với độ dài phim (duration)
//		LocalTime startTime = showtimeRequestDto.getStartTime().toLocalTime();
//		LocalTime endTime = startTime.plusMinutes((long) movie.getDuration());  // Thêm duration vào startTime
//
//		// Chuyển endTime thành đối tượng Time
//		Time endTimeObj = Time.valueOf(endTime);
//
//		// Cập nhật các trường thông tin của Showtime
//		showtime.setDate(showtimeRequestDto.getDate());
//		showtime.setStartTime(showtimeRequestDto.getStartTime());
//		showtime.setEndTime(endTimeObj);  // Cập nhật endTime đã tính toán
////		showtime.setStatus(showtimeRequestDto.isStatus());
//
//		showtimeRepository.save(showtime);
//	}
@Override
public Showtime updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto) {
	// Tìm lịch chiếu (Showtime) theo ID
	Showtime showtime = showtimeRepository.findById(showtimeId)
			.orElseThrow(() -> new EntityNotFoundException("Showtime not found"));

	// Kiểm tra các thông tin liên quan (Movie, Theater, Room)
	Theater theater = theaterRepository.findById(showtimeRequestDto.getTheaterId())
			.orElseThrow(() -> new EntityNotFoundException("Theater not found"));

	Movie movie = movieRepository.findById(showtimeRequestDto.getMovieId())
			.orElseThrow(() -> new EntityNotFoundException("Movie not found"));

	Room room = roomRepository.findById(showtimeRequestDto.getRoomId())
			.orElseThrow(() -> new EntityNotFoundException("Room not found"));

	// Lấy BasePrice mới nhất
	BasePrice basePrice = basePriceRepository.findTopByOrderByCreatedAtDesc()
			.orElseThrow(() -> new EntityNotFoundException("Base price not found"));

	// Tính toán thời gian kết thúc mới
	LocalTime startTime = showtimeRequestDto.getStartTime().toLocalTime();
	LocalTime endTime = startTime.plusMinutes((long) movie.getDuration());
	Time endTimeObj = Time.valueOf(endTime);

	// Kiểm tra xung đột lịch chiếu
	if (isScheduleConflict(showtimeRequestDto.getRoomId(), showtimeRequestDto.getDate(),
			showtimeRequestDto.getStartTime(), endTimeObj, showtimeId)) {
		// Trả về thông báo lỗi giữ nguyên lịch chiếu cũ
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Lịch chiếu bị trùng với lịch chiếu khác trong cùng phòng và ngày.");
	}

	// Tính toán DayOfWeek mới
	LocalDate localDate = showtimeRequestDto.getDate().toLocalDate();
	DayOfWeek dayOfWeek = dayOfWeekRepository.findByName(getDayOfWeekFromDate(localDate));
	if (dayOfWeek == null) {
		throw new EntityNotFoundException("DayOfWeek not found");
	}

	// Tìm TimeFrame mới dựa trên startTime
	TimeFrame timeFrame = getTimeFrameFromTime(startTime);

	// Tính lại giá vé
	Float priceTicket = basePrice.getDefaultPrice() + dayOfWeek.getSurcharge() + timeFrame.getSurcharge();

	// Cập nhật thông tin lịch chiếu
	showtime.setMovie(movie);
	showtime.setRoom(room);
	showtime.setDate(showtimeRequestDto.getDate());
	showtime.setStartTime(showtimeRequestDto.getStartTime());
	showtime.setEndTime(endTimeObj);
	showtime.setBasePrice(basePrice);
	showtime.setDayOfWeek(dayOfWeek);
	showtime.setTimeFrame(timeFrame);
	showtime.setPriceTicket(priceTicket);

	// Lưu lại lịch chiếu
	return showtimeRepository.save(showtime);
}




	// Delete a showtime by ID
	@Override
	public void deleteShowtime(long id) {
		showtimeRepository.deleteById(id);
	}

	// Hide showtimes when the movie is no longer available
	@Override
	@Transactional
	public void hideShowtimesByMovie(long movieId) {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new EntityNotFoundException("Movie not found"));

		System.out.println("Movie Status: " + movie.isStatus());  // Debugging

		if (!movie.isStatus()) {
			System.out.println("Hiding showtimes for movie ID: " + movieId);  // Debugging
			showtimeRepository.hideShowtimesByMovieStatus(movieId);

			// Optional: Flush lại để đảm bảo dữ liệu được cập nhật vào cơ sở dữ liệu
			showtimeRepository.flush();
		} else {
			System.out.println("Movie is active, no action taken.");  // Debugging
		}
	}
	//	// Lấy danh sách lịch chiếu theo theaterId
	// Lấy danh sách phòng chiếu của một rạp
	public List<RoomShowtimeDto> getRoomsByTheater(long theaterId) {
		// Lấy danh sách phòng chiếu từ repository
		List<RoomShowtimeDto> rooms = showtimeRepository.findRoomsByTheater(theaterId);

		// Duyệt qua từng phòng và lấy lịch chiếu tương ứng
		for (RoomShowtimeDto room : rooms) {
			List<ShowtimeDto> showtimes = showtimeRepository.findShowtimesByDateAndRoom(LocalDate.now(), room.getRoomId());
			room.setShowtimes(showtimes);  // Gán danh sách lịch chiếu cho phòng
		}

		return rooms;
	}

	// Lấy danh sách lịch chiếu của một phòng chiếu vào ngày cụ thể
	public List<ShowtimeDto> getShowtimesByDateAndRoom(LocalDate date, long roomId) {
		return showtimeRepository.findShowtimesByDateAndRoom(date, roomId);
	}

	// Lấy thông tin chi tiết của một lịch chiếu
	@Override
	public Optional<Showtime> getShowtimeById(long showtimeId) {
		return showtimeRepository.findById(showtimeId);
	}
	public ShowtimeDetailDto getShowtimeDetailById(long id) {
		Optional<Showtime> showtimeOptional = showtimeRepository.findById(id);
		if (showtimeOptional.isPresent()) {
			Showtime showtime = showtimeOptional.get();
			return showtime.toShowtimeDetailDto();  // Chuyển đổi thành ShowtimeDetailDto
		}
		return null;  // Nếu không tìm thấy lịch chiếu, trả về null
	}

}
