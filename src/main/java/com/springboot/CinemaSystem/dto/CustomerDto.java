package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Level;
import com.springboot.CinemaSystem.entity.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long ID;
    private boolean gender;
    private Date dob;
    private String address;
    private String email;
    private String phone;
    private String image;
    private Date startDate;
    private String fullname;
    private int points;
    private LevelDto level;
    private List<DiscountDto> discounts;


}
