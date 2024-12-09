package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Booking;
import com.springboot.CinemaSystem.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class FeedbackDto {
    private long ID;
    private String text;
    private LocalDateTime date;
    private int star;
    private BookingDto booking;

    public FeedbackDto(long ID, String text, LocalDateTime date, int star, BookingDto booking) {
        this.ID=ID;
        this.text=text;
        this.date=date;
        this.star=star;
        this.booking=booking;
    }
}
