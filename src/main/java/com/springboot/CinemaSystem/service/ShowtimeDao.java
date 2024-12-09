package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.ShowtimeDto;
import com.springboot.CinemaSystem.dto.ShowtimeRequestDto;
import com.springboot.CinemaSystem.dto.ShowtimeRoomDto;
import com.springboot.CinemaSystem.entity.BasePrice;
import com.springboot.CinemaSystem.entity.DayOfWeek;
import com.springboot.CinemaSystem.entity.Showtime;
import com.springboot.CinemaSystem.entity.TimeFrame;
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


//	public List<ShowtimeDto> getShowtimesByDateAndRoom(long theaterId, LocalDate date);

	public void updateShowtimeStatus();


	public void deleteShowtime(long showtimeId);

	public void toggleShowtimeStatus(long showtimeId);

	public void hideShowtimesByMovie(long movieId);

	public Showtime scheduleShowtime(ShowtimeRequestDto dto);


	public BasePrice getBasePrice();
	public List<DayOfWeek> getAllDayOfWeeks();
	public DayOfWeek getDayOfWeekById(Long key);
	public void updateDayOfWeek(DayOfWeek dayOfWeek);
	public List<TimeFrame> getAllTimeFrames();
	public TimeFrame getTimeFrameById(Long key);
	public void updateTimeFrame(TimeFrame timeFrame);
}