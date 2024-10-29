package com.springboot.CinemaSystem.entity;

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
	private SeatTicket seatTicket;

	@ManyToOne
	@JoinColumn(name = "showtimeID")
	private Showtime showtime;

	@ManyToOne
	@JoinColumn(name = "userID")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "paymentID")
	private Payment payment;

	@OneToOne(mappedBy = "ticketBought")
	private Feedback feedback;

}