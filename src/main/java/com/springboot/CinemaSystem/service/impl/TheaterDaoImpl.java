package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.MovieShowtimeDto;
import com.springboot.CinemaSystem.dto.ShowtimeMovieDto;
import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TheaterDaoImpl implements TheaterDao {
	private TheaterRepository theaterRepository;
	private RoomRepository roomRepository;
	private TypeRoomRepository typeRoomRepository;
	private TypeSeatRepository typeSeatRepository;


	@Autowired
	public TheaterDaoImpl(TheaterRepository theaterRepository, RoomRepository roomRepository, TypeRoomRepository typeRoomRepository, TypeSeatRepository typeSeatRepository) {
		this.theaterRepository = theaterRepository;
		this.roomRepository = roomRepository;
		this.typeRoomRepository = typeRoomRepository;
		this.typeSeatRepository = typeSeatRepository;
	}

	@Override
	public Theater addTheater(Theater theater) {
		try {
			return theaterRepository.save(theater);

		} catch (Exception e) {
			throw new DataProcessingException("Failed to add theater: " + e.getMessage());
		}
	}

	@Override

	public Theater updateTheater(Theater theater) {
		if (!theaterRepository.existsById(theater.getID())) {
			throw new NotFoundException("Cannot update: Theater not found with ID: " + theater.getID());
		}
		try {

			return theaterRepository.save(theater);

		} catch (Exception e) {
			throw new DataProcessingException("Failed to update theater: " + e.getMessage());
		}
	}

	@Override
	public boolean updateStatusTheater(long theaterID) {

		Theater theater = theaterRepository.findById(theaterID)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + theaterID));
		theater.setStatus(!theater.isStatus());
		theaterRepository.save(theater);
		return true;
	}

	@Override
	public boolean deleteTheater(long id) {
		Theater theater = theaterRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + id));
		try {
			theaterRepository.delete(theater);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Error delete theater with id: " + id + e.getMessage());
		}
	}

	@Override
	public Theater getTheaterByID(long theaterID) {
		return theaterRepository.findById(theaterID)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + theaterID));
	}

	@Override
	public List<Theater> getAllTheater() {
		try {
			return theaterRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Failed to retrieve theaters: " + e.getMessage());
		}
	}

	@Override
	public TheaterDto getShowtimeByTheater(Theater theater) {
		List<Showtime> showtimes = new ArrayList<>();
		for(Room room : theater.getRoom()) {
			if(room.isStatus()){
				showtimes.addAll(room.getShowtime());
			}
		}
		List<ShowtimeMovieDto> showtimeMovieDtos = showtimes.stream()
				.filter(showtime -> showtime.getAction().equals("upcoming")
						|| showtime.getAction().equals("running"))
				.map(showtime -> new ShowtimeMovieDto(
						showtime.getID(),
						showtime.getDate(),
						showtime.getStartTime(),
						showtime.getEndTime(),
						showtime.getAction(),
						showtime.getMovie().toMovieShowtimeDto()
				))
				.collect(Collectors.toList());

		Map<MovieShowtimeDto, List<ShowtimeMovieDto>> movieListMap = showtimeMovieDtos.stream()
				.collect(Collectors.groupingBy(ShowtimeMovieDto::getMovie));

		List<MovieShowtimeDto> movieShowtimeDtos = movieListMap.entrySet().stream()
				.map(entry -> new MovieShowtimeDto(
						entry.getKey().getId(),
						entry.getKey().getTitle(),
						entry.getKey().getDuration(),
						entry.getKey().getDescription(),
						entry.getKey().getImage(),
						entry.getValue().stream()
								.map(showtime -> new ShowtimeMovieDto(
										showtime.getID(),
										showtime.getDate(),
										showtime.getStartTime(),
										showtime.getEndTime(),
										showtime.getAction()
								))
								.collect(Collectors.toList())
				))
				.collect(Collectors.toList());
		return new TheaterDto(theater.getID(), theater.getName(), movieShowtimeDtos);
	}

	@Override
	public Room addRoom(Room room) {
		try {
			return roomRepository.save(room);
		} catch (Exception e) {
			throw new DataProcessingException("Lỗi thêm phòng: " + e.getMessage());
		}
	}

	@Override
	public boolean updateStatusRoom(long roomID) {
		Room room = roomRepository.findById(roomID)
				.orElseThrow(() -> new NotFoundException("Error get room with id: " + roomID));
		room.setStatus(!room.isStatus());
		roomRepository.save(room);
		return true;
	}

	@Override
	public boolean updateRoom(Room room) {
		if (!roomRepository.existsById(room.getID())) {
			throw new NotFoundException("Cannot update: Room not found with ID: " + room.getID());
		}
		try {
			roomRepository.save(room);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Failed to update room: " + e.getMessage());
		}
	}

	@Override
	public Room getRoomByID(long roomID) {
		return roomRepository.findById(roomID)
				.orElseThrow(() -> new NotFoundException("Not find room in database: " + roomID));
	}

	@Override
	public TypeRoom getTypeRoomByID(long typeRoomID) {
		return typeRoomRepository.findById(typeRoomID)
				.orElseThrow(() -> new NotFoundException("Error get typeroom with id: " + typeRoomID));
	}

	@Override
	public List<TypeRoom> getAllTypeRooms() {
		try {
			return typeRoomRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Không lấy được danh sách loại phòng: " + e.getMessage());
		}
	}

	@Override
	public void updateTypeRoom(TypeRoom typeRoom) {
		if(!typeRoomRepository.existsById(typeRoom.getID())){
			throw new NotFoundException("Error get typeroom by id");
		}
		typeRoomRepository.save(typeRoom);
	}

	@Override
	public TypeSeat getTypeSeatByID(long typeSeatID) {
		return typeSeatRepository.findById(typeSeatID)
				.orElseThrow(() -> new NotFoundException("Error get typeseat by ID"));
	}

	@Override
	public List<TypeSeat> getAllTypeSeats() {
		try {
			return typeSeatRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Không lấy được danh sách loại ghế: " + e.getMessage());
		}
	}

	@Override
	public void updateTypeSeat(TypeSeat typeSeat) {
		if(!typeSeatRepository.existsById(typeSeat.getID())){
			throw new NotFoundException("Error get typeseat by id");
		}
		typeSeatRepository.save(typeSeat);
	}

}