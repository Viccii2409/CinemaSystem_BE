package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDto {
    private long ID;
    private String name;
    private String address;
    private int quantityRoom;
    private boolean status;

    public TheaterDto(long ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}

