package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.TheaterRepository;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TheaterDaoImpl implements TheaterDao {

	@Autowired
	private TheaterRepository theaterRepository;

	@Override
	public boolean addTheater(Theater theater) {
		try {
			theaterRepository.save(theater);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Failed to add theater: " + e.getMessage());
		}
	}

	@Override
	public boolean updaeTheater(Theater theater) {
		if (!theaterRepository.existsById(theater.getID())) {
			throw new NotFoundException("Cannot update: Theater not found with ID: " + theater.getID());
		}
		try {
			theaterRepository.save(theater);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Failed to update theater: " + e.getMessage());
		}
	}

	@Override
	public Theater updateStatusTheater(int theaterID) {
		return null;
	}

	@Override
	public Theater getTheaterByID(int theaterID) {
		return theaterRepository.findById((long) theaterID)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + theaterID));
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
	public boolean addRoom(Room room) {
		return false;
	}

	@Override
	public boolean updateStatusRoom(int roomID) {
		return false;
	}

	@Override
	public List<Room> getRoomByTheater(int theaterID) {
		return List.of();
	}

	@Override
	public Room getRoomByID(int roomID) {
		return null;
	}

	@Override
	public List<Room> getRoomsByTypeRoom(int typeRoomID, int theaterID) {
		return List.of();
	}

	@Override
	public boolean addTypeRoom(TypeRoom typeRoom) {
		return false;
	}

	@Override
	public TypeRoom getTypeRoomByID(int typeRoomID) {
		return null;
	}

	@Override
	public List<TypeRoom> getAllTypeRooms() {
		return List.of();
	}

	@Override
	public boolean addSeat(Seat seat) {
		return false;
	}

	@Override
	public boolean editSeat(Seat seat) {
		return false;
	}

	@Override
	public List<Seat> getSeatByRoom(int roomID) {
		return List.of();
	}

	@Override
	public Seat getSeatByID(int seatID) {
		return null;
	}

	@Override
	public boolean addTypeSeat(TypeSeat typeSeat) {
		return false;
	}

	@Override
	public TypeSeat getTypeSeatByID(int typeSeatID) {
		return null;
	}

	@Override
	public List<TypeSeat> getAllTypeSeats() {
		return List.of();
	}

	@Override
	public float getTheaterStat(Date startDate, Date endDate) {
		return 0;
	}
}