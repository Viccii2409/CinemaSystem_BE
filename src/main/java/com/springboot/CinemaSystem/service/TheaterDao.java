package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.entity.*;

import java.util.Date;
import java.util.List;


public interface TheaterDao {
	public Theater addTheater(Theater theater);
	public Theater updateTheater(Theater theater);
	public boolean updateStatusTheater(long theaterID);
	public Theater getTheaterByID(long theaterID);
	public List<Theater> getAllTheaters();
	public List<String> getAllTheaterName();
	public List<TheaterDto> getAllTheaterDto();
	public boolean addRoom(Room room);
	public boolean updateStatusRoom(long roomID);
	public List<Room> getRoomByTheater(long theaterID);
	public Room getRoomByID(long roomID);
	public List<Room> getRoomsByTypeRoom(long typeRoomID, long theaterID);
	public boolean addTypeRoom(TypeRoom typeRoom);
	public TypeRoom getTypeRoomByID(long typeRoomID);
	public List<TypeRoom> getAllTypeRooms();
	boolean addSeat(Seat seat);
	boolean editSeat(Seat seat);
	public List<Seat> getSeatByRoom(long roomID);
	public Seat getSeatByID(long seatID);
	public boolean addTypeSeat(TypeSeat typeSeat);
	public TypeSeat getTypeSeatByID(long typeSeatID);
	public List<TypeSeat> getAllTypeSeats();
	public float getTheaterStat(Date startDate, Date endDate);

}