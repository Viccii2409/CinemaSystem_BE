	package com.springboot.CinemaSystem.service;

	import com.springboot.CinemaSystem.entity.*;

	import java.util.Date;
	import java.util.List;

	public interface TheaterDao {
		public boolean addTheater(Theater theater);
		public boolean updaeTheater(Theater theater);
		public Theater updateStatusTheater(int theaterID);
		public Theater getTheaterByID(int theaterID);
		public List<Theater> getAllTheaters();
		public boolean addRoom(Room room);
		public boolean updateStatusRoom(int roomID);
		public List<Room> getRoomByTheater(int theaterID);
		public Room getRoomByID(int roomID);
		public List<Room> getRoomsByTypeRoom(int typeRoomID, int theaterID);
		public boolean addTypeRoom(TypeRoom typeRoom);
		public TypeRoom getTypeRoomByID(int typeRoomID);
		public List<TypeRoom> getAllTypeRooms();
		boolean addSeat(Seat seat);
		boolean editSeat(Seat seat);
		public List<Seat> getSeatByRoom(int roomID);
		public Seat getSeatByID(int seatID);
		public boolean addTypeSeat(TypeSeat typeSeat);
		public TypeSeat getTypeSeatByID(int typeSeatID);
		public List<TypeSeat> getAllTypeSeats();
		public float getTheaterStat(Date startDate, Date endDate);

	}