package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.TypeSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private long id;
    private String name;
    private int seatNum;
    private int rowNum;
    private boolean status;
    private TypeSeatDto typeSeat;

}
