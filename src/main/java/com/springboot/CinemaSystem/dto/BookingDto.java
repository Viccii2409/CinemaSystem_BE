package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private long ID;
    private LocalDateTime dateBooking;
    private String barcode;
    private String typeBooking;


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


    private String barcodePayment;
    private String statusPayment;
    private FeedbackDto feedback;

    public BookingDto(long ID, LocalDateTime dateBooking, String barcode, List<String> nameSeats, Date dateShowtime, Time startTime, Time endTime, String nameMovie, String image, String nameTheater, String address, String nameRoom, String typeRoom, float totalPrice, float discountPrice, float amount, String nameCustomer, String phone, String email) {
        this.ID = ID;
        this.dateBooking = dateBooking;
        this.barcode = barcode;
        this.nameSeats = nameSeats;
        this.dateShowtime = dateShowtime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nameMovie = nameMovie;
        this.image = image;
        this.nameTheater = nameTheater;
        this.address = address;
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.amount = amount;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.email = email;
    }
}