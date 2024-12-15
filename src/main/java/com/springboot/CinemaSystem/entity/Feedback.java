package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
}
