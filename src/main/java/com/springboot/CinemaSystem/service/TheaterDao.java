package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface TheaterDao {

	public Theater addTheater(Theater theater);
	public Theater updateTheater(Theater theater);
	public boolean updateStatusTheater(long theaterID);
	public boolean deleteTheater(long id);
	public Theater getTheaterByID(long theaterID);
	public List<Theater> getAllTheater();
	public TheaterDto getShowtimeByTheater(Theater theater);

	public Room addRoom(Room room);
	public boolean updateStatusRoom(long roomID);
	public boolean updateRoom(Room room);
	public Room getRoomByID(long roomID);

	public TypeRoom getTypeRoomByID(long typeRoomID);
	public List<TypeRoom> getAllTypeRooms();
	public void updateTypeRoom(TypeRoom typeRoom);

	public TypeSeat getTypeSeatByID(long typeSeatID);
	public List<TypeSeat> getAllTypeSeats();
	public void updateTypeSeat(TypeSeat typeSeat);
}