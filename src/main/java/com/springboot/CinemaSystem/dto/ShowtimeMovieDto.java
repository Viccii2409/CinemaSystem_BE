package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeMovieDto {
    private long ID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String action;
    private MovieShowtimeDto movie;

    public ShowtimeMovieDto(long ID, Date date, Time startTime, Time endTime, String action) {
        this.ID = ID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.action = action;
    }
}
