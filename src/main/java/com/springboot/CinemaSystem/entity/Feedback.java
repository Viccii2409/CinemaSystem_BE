package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackID")
	private long ID;

	@Lob
	@Column(name = "text", columnDefinition = "TEXT")
	private String text;
	private LocalDateTime date;

	private int star;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonIgnoreProperties("feedback")
	private Movie movie;

	@OneToOne
	@JoinColumn(name = "bookingID")
	private Booking booking;

	public FeedbackDto toFeedbackDto() {
		BookingDto bookingDto = new BookingDto(booking.getID(),
				booking.getShowtime().getMovie().getId(),
				booking.getUser().getName(),
				booking.getUser().getImage());
		return new FeedbackDto(this.ID, this.text, this.date, this.star,bookingDto);	}


}