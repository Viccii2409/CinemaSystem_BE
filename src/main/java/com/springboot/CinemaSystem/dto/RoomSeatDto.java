package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSeatDto {
    private long id;
    private String name;
    private TypeRoomDto typeRoom;
    private int quantitySeat;
    private int numRows;
    private int numColumn;
    private boolean status;
    private List<SeatDto> seat;
    private String theaterName;
}
