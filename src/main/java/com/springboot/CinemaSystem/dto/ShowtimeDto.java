package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeDto {
    private long ID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String action;
    private MovieShowtimeDto movie;

    public ShowtimeDto(long ID, Date date, Time startTime, Time endTime, String action) {
        this.ID = ID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.action = action;
    }
}
