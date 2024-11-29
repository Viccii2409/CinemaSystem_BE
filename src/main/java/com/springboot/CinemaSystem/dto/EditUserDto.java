package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDto {
    private Long id;
    private String name;
    private String address;
    private Date dob;
    private String password;
    private String gender;
    private String phone;
}
