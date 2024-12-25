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
	public ShowtimeRoomDto getRoomByShowtime(long id) {
		Showtime showtime = showtimeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
		return showtime.toShowtimeRoomDto();
	}


	@Override
	public Showtime getShowtimeByID(long showtimeID) {
		return showtimeRepository.findById(showtimeID)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
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

		// Tính toán endTime
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
		LocalDate localDate = dto.getDate().toLocalDate();
		DayOfWeek dayOfWeek = dayOfWeekRepository.findByName(getDayOfWeekFromDate(localDate));
		if (dayOfWeek == null) {
			throw new EntityNotFoundException("DayOfWeek not found");
		}

		// 2. Tìm TimeFrame dựa trên startTime
		TimeFrame timeFrame = getTimeFrameFromTime(startTime);

		// 3. Tính giá vé (priceTicket) dựa trên BasePrice, DayOfWeek, và TimeFrame
		Float priceTicket = basePrice.getDefaultPrice() + dayOfWeek.getSurcharge() + timeFrame.getSurcharge();

		Showtime showtime = new Showtime();
		showtime.setMovie(movie);
		showtime.setRoom(room);
		showtime.setDate(dto.getDate());
		showtime.setStartTime(dto.getStartTime());
		showtime.setEndTime(endTimeObj);
		showtime.setBasePrice(basePrice);
		showtime.setDayOfWeek(dayOfWeek);
		showtime.setTimeFrame(timeFrame);
		showtime.setPriceTicket(priceTicket);
		showtime.setAction("upcoming");
		showtime.setStatus(true);
		return showtimeRepository.save(showtime);
	}


	// Phương thức này để lấy tên ngày trong tuần từ LocalDate
	public String getDayOfWeekFromDate(LocalDate date) {
		// Lấy tên ngày trong tuần từ LocalDate
		String dayOfWeekName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("vi"));

		// Xử lý tên ngày
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
				return "Từ thứ 2 đến thứ 6";
		}
	}

	// Phương thức tìm TimeFrame dựa trên startTime
	public TimeFrame getTimeFrameFromTime(LocalTime startTime) {
		return timeFrameRepository.findByTimeStartBeforeAndTimeEndAfter(startTime, startTime)
				.orElseThrow(() -> new EntityNotFoundException("TimeFrame not found"));
	}


	// Check for schedule conflict
	private boolean isScheduleConflict(long roomId, Date date, Time startTime, Time endTime, Long showtimeId) {
		List<Showtime> conflictingShowtimes;
		if (showtimeId != null) {
			conflictingShowtimes = showtimeRepository.findConflictingShowtimesExcludeCurrent(roomId, date, startTime, endTime, showtimeId);
		} else {
			conflictingShowtimes = showtimeRepository.findConflictingShowtimes(roomId, date, startTime, endTime);
		}
		return !conflictingShowtimes.isEmpty();
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


@Override
public Showtime updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto) {
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

	showtime.setMovie(movie);
	showtime.setRoom(room);
	showtime.setDate(showtimeRequestDto.getDate());
	showtime.setStartTime(showtimeRequestDto.getStartTime());
	showtime.setEndTime(endTimeObj);
	showtime.setBasePrice(basePrice);
	showtime.setDayOfWeek(dayOfWeek);
	showtime.setTimeFrame(timeFrame);
	showtime.setPriceTicket(priceTicket);

	return showtimeRepository.save(showtime);
}




	// Delete a showtime by ID
	@Override
	public void deleteShowtime(long id) {
		showtimeRepository.deleteById(id);
	}

	//	// Lấy danh sách lịch chiếu theo theaterId
	// Lấy danh sách phòng chiếu của một rạp
	public List<RoomShowtimeDto> getRoomsByTheater(long theaterId) {
		List<RoomShowtimeDto> rooms = showtimeRepository.findRoomsByTheater(theaterId);

		// Duyệt qua từng phòng và lấy lịch chiếu tương ứng
		for (RoomShowtimeDto room : rooms) {
			List<ShowtimeDto> showtimes = showtimeRepository.findShowtimesByDateAndRoom(LocalDate.now(), room.getRoomId());
			room.setShowtimes(showtimes);
		}

		return rooms;
	}

	// Lấy danh sách lịch chiếu của một phòng chiếu vào ngày cụ thể
	public List<ShowtimeDto> getShowtimesByDateAndRoom(LocalDate date, long roomId) {
		return showtimeRepository.findShowtimesByDateAndRoom(date, roomId);
	}

	@Override
	public ShowtimeDetailDto getShowtimeDetailById(long id) {
		Optional<Showtime> showtimeOptional = showtimeRepository.findById(id);
		if (showtimeOptional.isPresent()) {
			Showtime showtime = showtimeOptional.get();
			return showtime.toShowtimeDetailDto();  // Chuyển đổi thành ShowtimeDetailDto
		}
		return null;
	}


	@Override
	public BasePrice getBasePrice() {
		try {
			return basePriceRepository.findTopByOrderByIDDesc();
		} catch (Exception e) {
			throw new NotFoundException("Error get baseprice" + e.getMessage());
		}
	}

	@Override
	public List<DayOfWeek> getAllDayOfWeeks() {
		try {
			return dayOfWeekRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error get all day of week" + e.getMessage());
		}
	}

	@Override
	public DayOfWeek getDayOfWeekById(Long key) {
		return dayOfWeekRepository.findById(key)
				.orElseThrow(() -> new NotFoundException("Error get dayofweek by ID"));
	}

	@Override
	public void updateDayOfWeek(DayOfWeek dayOfWeek) {
		if(!dayOfWeekRepository.existsById(dayOfWeek.getID())){
			throw new NotFoundException("Error get dayofweek by ID");
		}
		dayOfWeekRepository.save(dayOfWeek);
	}

	@Override
	public List<TimeFrame> getAllTimeFrames() {
		try {
			return timeFrameRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error get all timeframe" + e.getMessage());
		}
	}

	@Override
	public TimeFrame getTimeFrameById(Long key) {
		return timeFrameRepository.findById(key)
				.orElseThrow(() -> new NotFoundException("Error timeframe by ID"));
	}

	@Override
	public void updateTimeFrame(TimeFrame timeFrame) {
		if(!timeFrameRepository.existsById(timeFrame.getID())){
			throw new NotFoundException("Error timeframe by ID");
		}
		timeFrameRepository.save(timeFrame);
	}

}
