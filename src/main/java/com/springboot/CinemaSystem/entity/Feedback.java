package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackID")
	private long ID;
	private String text;
	private String date;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonBackReference
	private Movie movie;

	@OneToOne
	@JoinColumn(name = "ticketBoughtID")
	private TicketBought ticketBought;

	@ManyToOne
	@JoinColumn(name = "ratingID")
	private Rating rating;

}