package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long ID;
    private String gender;
    private Date dob;
    private String address;
    private String email;
    private String phone;
    private String image;
    private LocalDateTime startDate;
    private int points;
    private LevelDto level;
    private List<DiscountDto> discounts;

    private String name;
    private String username;
    private List<BookingDto> bookings;

}
