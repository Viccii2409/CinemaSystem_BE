package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Address;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterViewDto {
    private long ID;
    private String name;
    private String description;
    private String phone;
    private String email;
    private String image;
    private int quantityRoom;
    private boolean status;
    private Address address;
}
