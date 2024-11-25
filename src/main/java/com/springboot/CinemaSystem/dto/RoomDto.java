package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private long id;
    private String name;
    private TypeRoomDto typeRoom;
    private int quantitySeat;
    private boolean status;
}
