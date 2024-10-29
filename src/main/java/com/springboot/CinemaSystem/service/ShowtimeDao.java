package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Showtime;

import java.util.List;

public interface ShowtimeDao {
	public boolean addShowtime(Showtime showtime);
	public boolean updateShowtime(Showtime showtime);
	public List<Showtime> getShowtimeByMovie(int movieID);
	public boolean updateStatusShowtime(int showtimeID);
	public Showtime getShowtimeByID(int showtimeID);
	public List<Showtime> getShowtimesByRoomID(int roomID);
	public boolean checkAvailability(int showtimeID);


}