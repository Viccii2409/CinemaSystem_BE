package com.springboot.CinemaSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeRoomDto {
    private long ID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private boolean status;
    private String action;

    private float priceTicket;

    private MovieShowtimeDto movie;
    private RoomDto room;
    private List<SelectedSeatDto> selectedSeats;
}
