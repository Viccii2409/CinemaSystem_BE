package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedSeatDto {
    private long id;
    private long userid;
    private long seatid;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;
}
