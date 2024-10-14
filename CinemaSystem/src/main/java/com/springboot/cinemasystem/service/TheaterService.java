package com.springboot.cinemasystem.service;

import java.util.List;

import com.springboot.cinemasystem.model.entity.Room;
import com.springboot.cinemasystem.model.entity.Seat;
import com.springboot.cinemasystem.model.entity.Theater;

public interface TheaterService {

	/**
	 * 
	 * @param theaterID
	 */
	Theater getTheater(int theaterID);

	/**
	 * 
	 * @param theater
	 */
	boolean addTheater(Theater theater);

	/**
	 * 
	 * @param theater
	 */
	boolean editTheater(Theater theater);

	/**
	 * 
	 * @param theaterID
	 */
	boolean updateStatusTheater(int theaterID);

	/**
	 * 
	 * @param roomID
	 */
	boolean getRoom(int roomID);

	/**
	 * 
	 * @param room
	 */
	boolean addRoom(Room room);

	/**
	 * 
	 * @param room
	 */
	boolean editRoom(Room room);

	/**
	 * 
	 * @param roomID
	 */
	boolean updateStatusRoom(int roomID);

	/**
	 * 
	 * @param seat
	 */
	boolean addSeat(Seat seat);

	/**
	 * 
	 * @param seat
	 */
	boolean editSeat(Seat seat);

	/**
	 * 
	 * @param seatID
	 */
	Seat getSeat(int seatID);

	/**
	 * 
	 * @param theaterID
	 */
	List<Room> getRoomByTheater(int theaterID);

	/**
	 * 
	 * @param roomID
	 */
	List<Seat> getSeatByRoom(int roomID);

	List<Theater> getAllTheater();

}