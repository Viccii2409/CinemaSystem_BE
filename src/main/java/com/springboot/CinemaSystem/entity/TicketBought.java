package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TicketBought {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketBoughtID")
	private long ID;
	private String date;

	@OneToOne
	@JoinColumn(name = "seatTicketID")
	@JsonManagedReference
	private SeatTicket seatTicket;

	@ManyToOne
	@JoinColumn(name = "showtimeID")
	@JsonManagedReference
	private Showtime showtime;

	@ManyToOne
	@JoinColumn(name = "userID")
	@JsonBackReference
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "paymentID")
	@JsonBackReference
	private Payment payment;

	@OneToOne(mappedBy = "ticketBought")
	@JsonBackReference
	private Feedback feedback;

}