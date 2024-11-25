package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeTheaterIDDto {
    private long ID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String action;
    private long theaterID;
    private int emptySeat;
    private String typeRoomName;
}