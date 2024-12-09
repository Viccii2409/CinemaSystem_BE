package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVerifyDto {
    private long ID;
    private String name;
    private Date dob;
    private String address;
    private String email;
    private String phone;
    private String image;
    private boolean status;
    private RoleDto role;
    private List<DiscountDto> discounts;
}
