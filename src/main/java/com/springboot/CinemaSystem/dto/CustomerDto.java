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

    public CustomerDto(long ID, String gender, Date dob, String address, String email, String phone, String image, LocalDateTime startDate, String username, int points, LevelDto level, List<DiscountDto> discounts) {
        this.ID = ID;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.startDate = startDate;
        this.username = username;
        this.points = points;
        this.level = level;
        this.discounts = discounts;
    }

    public CustomerDto(long ID, String gender, Date dob, String address, String email, String phone, String image, LocalDateTime startDate, String username, int points, LevelDto level, List<DiscountDto> discounts, String name, List<BookingDto> bookings) {
        this.ID = ID;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.startDate = startDate;
        this.username = username;
        this.points = points;
        this.level = level;
        this.discounts = discounts;
        this.name = name;
        this.bookings = bookings;
    }
}
