package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.ShowtimeRoomDto;
import com.springboot.CinemaSystem.entity.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShowtimeDao {
	public void updateShowtimeAction();
	public ShowtimeRoomDto getRoomByShowtime(long id);


	public boolean addShowtime(Showtime showtime);
	public boolean updateShowtime(Showtime showtime);
	public List<Showtime> getShowtimeByMovie(int movieID);
	public boolean updateStatusShowtime(int showtimeID);
	public Showtime getShowtimeByID(long showtimeID);
	public List<Showtime> getShowtimesByRoomID(int roomID);
	public boolean checkAvailability(int showtimeID);


}