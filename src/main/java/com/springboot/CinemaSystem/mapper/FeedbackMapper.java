package com.springboot.CinemaSystem.mapper;

import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.FeedbackAddDto;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.entity.Booking;
import com.springboot.CinemaSystem.entity.Feedback;
import com.springboot.CinemaSystem.entity.Movie;

import java.time.LocalDateTime;

public class FeedbackMapper {
    public static FeedbackDto toFeedbackDto(Feedback feedback) {
        return new FeedbackDto(feedback.getID(), feedback.getText(), feedback.getDate(),
                feedback.getStar(), new BookingDto(
                feedback.getBooking().getID(),
                feedback.getBooking().getShowtime().getMovie().getId(),
                feedback.getBooking().getUser().getName(),
                feedback.getBooking().getUser().getImage()
        ));
    }
    public static Feedback toFeedbackAdd(FeedbackAddDto dto, Booking booking){
        if (dto.getText() == null || dto.getStar() == null) {
            throw new IllegalArgumentException("Text and star fields are required");
        }
        Feedback feedback=new Feedback();
        feedback.setText(dto.getText());
        try {
            feedback.setStar(Integer.parseInt(dto.getStar()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid star value: " + dto.getStar());
        }
        // Set movie and booking for the feedback
        feedback.setBooking(booking);
        feedback.setMovie(booking.getShowtime().getMovie());
        // Set current date for the feedback
        feedback.setDate(LocalDateTime.now());
        return feedback;
    }
}
