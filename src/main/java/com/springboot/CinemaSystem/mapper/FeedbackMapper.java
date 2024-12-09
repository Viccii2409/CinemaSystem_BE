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
                feedback.getBooking().getShowtime().getMovie().getId()
        ));
    }
    public static Feedback toFeedbackAdd(FeedbackAddDto dto,Movie movie, Booking booking){
        if (dto.getText() == null || dto.getStar() == null) {
            throw new IllegalArgumentException("Text and star fields are required");
        }
        // Kiểm tra nếu movie và booking là null
        if (movie == null || booking == null) {
            throw new IllegalArgumentException("Movie and booking cannot be null");
        }
        Feedback feedback=new Feedback();
        feedback.setText(dto.getText());
        try {
            feedback.setStar(Integer.parseInt(dto.getStar()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid star value: " + dto.getStar());
        }
        // Set movie and booking for the feedback
        feedback.setMovie(movie);
        feedback.setBooking(booking);

        // Set current date for the feedback
        feedback.setDate(LocalDateTime.now());
        return feedback;
    }
}
