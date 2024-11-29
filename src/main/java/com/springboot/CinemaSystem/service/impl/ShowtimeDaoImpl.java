package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.dto.RoomSeatDto;
import com.springboot.CinemaSystem.dto.ShowtimeRoomDto;
import com.springboot.CinemaSystem.entity.Showtime;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.ShowtimeRepository;
import com.springboot.CinemaSystem.service.ShowtimeDao;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowtimeDaoImpl implements ShowtimeDao {
	@Autowired
	private ShowtimeRepository showtimeRepository;

	@Override
	@Scheduled(fixedRate = 60000*30)
//	@Scheduled(cron = "0 0 * * * *")	// chạy mỗi giờ
//	@Scheduled(cron = "*/5 * * * * *")
	@Transactional
	public void updateShowtimeAction() {
		LocalDate localDate = LocalDate.now();
		Date currentDate = Date.valueOf(localDate);
		LocalTime localTime = LocalTime.now();
		Time currentTime = Time.valueOf(localTime);
		try {
			showtimeRepository.updateActionToRunning(currentDate, currentTime);
			showtimeRepository.updateActionToEnded(currentDate, currentTime);
			showtimeRepository.updateActionToUpcoming(currentDate, currentTime);
		} catch (Exception e) {
			throw new DataProcessingException("Error update action showtime");
		}
	}

	@Override
	public ShowtimeRoomDto getRoomByShowtime(long id) {
		Showtime showtime = showtimeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
		return showtime.toShowtimeRoomDto();
	}

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
	public Showtime getShowtimeByID(long showtimeID) {
		return showtimeRepository.findById(showtimeID)
				.orElseThrow(() -> new NotFoundException("Error get showtime by id"));
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