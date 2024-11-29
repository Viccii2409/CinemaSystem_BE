package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private long ID;
    private LocalDateTime dateBooking;
    private String barcode;

    private List<String> nameSeats;

    private Date dateShowtime;
    private Time startTime;
    private Time endTime;

    private String nameMovie;
    private String image;

    private String nameTheater;
    private String address;
    private String nameRoom;
    private String typeRoom;

    private float totalPrice;
    private float discountPrice;
    private float amount;

    private String nameCustomer;
    private String phone;
    private String email;
}