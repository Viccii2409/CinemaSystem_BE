package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.RoomShowtimeDto;
import com.springboot.CinemaSystem.dto.ShowtimeDto;
import com.springboot.CinemaSystem.dto.ShowtimeRequestDto;
import com.springboot.CinemaSystem.dto.ShowtimeRoomDto;
import com.springboot.CinemaSystem.entity.Showtime;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeDao {
	public void updateShowtimeAction();
	public ShowtimeRoomDto getRoomByShowtime(long id);


	public boolean addShowtime(Showtime showtime);
	public void updateShowtime(long showtimeId, ShowtimeRequestDto showtimeRequestDto);
	public List<Showtime> getShowtimeByMovie(int movieID);
	public boolean updateStatusShowtime(int showtimeID);
	public Showtime getShowtimeByID(long showtimeID);
	public List<Showtime> getShowtimesByRoomID(int roomID);
	public boolean checkAvailability(int showtimeID);


//	public List<RoomShowtimeDto> getShowtimesByDateAndTheater(LocalDate date, long theaterId);
	public List<ShowtimeDto> getShowtimesByDateAndRoom(LocalDate date, long roomId);
	public List<RoomShowtimeDto> getRoomsByTheater(long theaterId);

	public void updateShowtimeStatus();

	public void deleteShowtime(long showtimeId);

	public void toggleShowtimeStatus(long showtimeId);

	public void hideShowtimesByMovie(long movieId);

	public Showtime scheduleShowtime(ShowtimeRequestDto dto);
}