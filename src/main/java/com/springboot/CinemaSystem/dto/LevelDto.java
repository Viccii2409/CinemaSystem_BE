package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto {
    private long ID;
    private String name;
    private int quantityTicket;
}
