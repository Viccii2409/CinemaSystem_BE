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

    private Name name;
    private List<BookingDto> bookings;

    public CustomerDto(long ID, boolean gender, Date dob, String address, String email, String phone, String image, Date startDate, String fullname, int points, LevelDto level, List<DiscountDto> discounts) {
        this.ID = ID;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.startDate = startDate;
        this.fullname = fullname;
        this.points = points;
        this.level = level;
        this.discounts = discounts;
    }

    public CustomerDto(long ID, boolean gender, Date dob, String address, String email, String phone, String image, Date startDate, String fullname, int points, LevelDto level, List<DiscountDto> discounts, Name name, List<BookingDto> bookings) {
        this.ID = ID;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.startDate = startDate;
        this.fullname = fullname;
        this.points = points;
        this.level = level;
        this.discounts = discounts;
        this.name = name;
        this.bookings = bookings;
    }
}
