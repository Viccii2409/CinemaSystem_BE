package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private boolean gender;
    private int points;
    private String levelName;
    private boolean status;


    // Getters and setters
}
