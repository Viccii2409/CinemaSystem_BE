package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.TheaterDao;

import java.util.Date;
import java.util.List;

public class TheaterDaoImpl implements TheaterDao {

	@Override
	public boolean addTheater(Theater theater) {
		return false;
	}

	@Override
	public boolean updaeTheater(Theater theater) {
		return false;
	}

	@Override
	public Theater updateStatusTheater(int theaterID) {
		return null;
	}

	@Override
	public Theater getTheaterByID(int theaterID) {
		return null;
	}

	@Override
	public List<Theater> getAllTheaters() {
		return List.of();
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