package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.dto.TheaterExceptDto;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TheaterDaoImpl implements TheaterDao {
	private TheaterRepository theaterRepository;
	private RoomRepository roomRepository;
	private TypeRoomRepository typeRoomRepository;
	private TypeSeatRepository typeSeatRepository;
	private SeatRepository seatRepository;

	@Autowired
	public TheaterDaoImpl(TheaterRepository theaterRepository, RoomRepository roomRepository, TypeRoomRepository typeRoomRepository, TypeSeatRepository typeSeatRepository, SeatRepository seatRepository) {
		this.theaterRepository = theaterRepository;
		this.roomRepository = roomRepository;
		this.typeRoomRepository = typeRoomRepository;
		this.typeSeatRepository = typeSeatRepository;
		this.seatRepository = seatRepository;
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
	public Theater getTheaterByID(long theaterID) {
		return theaterRepository.findById(theaterID)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + theaterID));
	}

	@Override

	public long getCountTheater() {
		return theaterRepository.count();
	}

	@Override

	public List<Theater> getAllTheaters() {
		try {
			return theaterRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Failed to retrieve theaters: " + e.getMessage());
		}
	}

	@Override
	public List<TheaterDto> getAllTheaterDto() {
		try {
			return theaterRepository.getListTheaterDto();
		} catch (Exception e) {
			throw new DataProcessingException("Failed to retrieve theaters: " + e.getMessage());
		}
	}
	@Override
	public List<TheaterExceptDto> getTheatersExcept(long theaterID) {
		return theaterRepository.findAllExcept(theaterID);
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
	public List<Room> getRoomByTheater(long theaterID) {
		try {
			return roomRepository.findByTheaterID(theaterID);
		} catch (Exception e) {
			throw new DataProcessingException("Failed to retrieve rooms: " + e.getMessage());
		}
	}

	@Override
	public Room getRoomByID(long roomID) {
		return roomRepository.findById(roomID)
				.orElseThrow(() -> new NotFoundException("Not find room in database: " + roomID));
	}

	@Override
	public List<Room> getRoomsByTypeRoom(long typeRoomID, long theaterID) {
		return List.of();
	}

	@Override
	public boolean addTypeRoom(TypeRoom typeRoom) {
		return false;
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
	public boolean addListSeat(List<Seat> seats) {
		try {
			seatRepository.saveAll(seats); // Lưu danh sách Seat
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Error add list room: " + e.getMessage());
		}

	}

	@Override
	public boolean addSeat(Seat seat) {
		return true;
	}

	@Override
	public boolean editSeat(Seat seat) {
		return false;
	}

	@Override
	public List<Seat> getSeatByRoom(long roomID) {
		return List.of();
	}

	@Override
	public Seat getSeatByID(long seatID) {
		return null;
	}

	@Override
	public boolean addTypeSeat(TypeSeat typeSeat) {
		return false;
	}

	@Override
	public TypeSeat getTypeSeatByID(long typeSeatID) {
		return null;
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
	public float getTheaterStat(Date startDate, Date endDate) {
		return 0;
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

}