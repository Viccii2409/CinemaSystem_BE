package com.springboot.CinemaSystem.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowtimeDetailDto {

    private long id;
    private long movieId;
    private long theaterId;
    private long roomId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String movieTitle;
    private String theaterName;
    private String roomName;
    private String status;

    // Constructor with all parameters
    public ShowtimeDetailDto(long id, long movieId, long theaterId, long roomId,
                             LocalDate date, LocalTime startTime, LocalTime endTime,
                             String movieTitle, String theaterName, String roomName,
                             String status) {
        this.id = id;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.roomId = roomId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.roomName = roomName;
        this.status = status;
    }
}
