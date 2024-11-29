package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import jakarta.persistence.*;
import lombok.Data;

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
	private String date;

	@ManyToOne
	@JoinColumn(name = "ratingID")
	private Rating rating;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonIgnoreProperties("feedback")
	private Movie movie;

	@OneToOne
	@JoinColumn(name = "bookingID")
	private Booking booking;

	public FeedbackDto toFeedbackDto() {
		return new FeedbackDto(this.ID, this.text, this.date, this.rating);
	}


}