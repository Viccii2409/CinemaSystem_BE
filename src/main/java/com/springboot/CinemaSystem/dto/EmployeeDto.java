package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private long ID;
    private String gender;
    private Date dob;
    private String address;
    private String email;
    private String phone;
    private String image;
    private LocalDateTime startDate;
    private List<DiscountDto> discounts;

    private String name;
    private String username;
    private List<BookingDto> bookings;
    private String position;
    private Date dayInWork;
    private double revenue;
}
