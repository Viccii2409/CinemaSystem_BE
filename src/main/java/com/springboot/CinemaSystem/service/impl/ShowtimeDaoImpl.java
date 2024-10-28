package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Showtime;
import com.springboot.CinemaSystem.service.ShowtimeDao;

import java.util.List;

public class ShowtimeDaoImpl implements ShowtimeDao {

	@Override
	public boolean addShowtime(Showtime showtime) {
		return false;
	}

	@Override
	public boolean updateShowtime(Showtime showtime) {
		return false;
	}

	@Override
	public List<Showtime> getShowtimeByMovie(int movieID) {
		return List.of();
	}

	@Override
	public boolean updateStatusShowtime(int showtimeID) {
		return false;
	}

	@Override
	public Showtime getShowtimeByID(int showtimeID) {
		return null;
	}

	@Override
	public List<Showtime> getShowtimesByRoomID(int roomID) {
		return List.of();
	}

	@Override
	public boolean checkAvailability(int showtimeID) {
		return false;
	}
}