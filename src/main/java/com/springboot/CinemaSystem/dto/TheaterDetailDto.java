package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDetailDto {
    private long ID;
    private String name;
    private String description;
    private String phone;
    private String email;
    private String image;
    private String address;
}
