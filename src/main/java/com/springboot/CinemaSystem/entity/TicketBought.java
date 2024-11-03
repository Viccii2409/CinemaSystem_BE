package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
	@JsonIdentityReference(alwaysAsId = true)
	private Showtime showtime;

	@ManyToOne
	@JoinColumn(name = "userID")
	@JsonIgnoreProperties("ticketBought")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "paymentID")
	private Payment payment;

	@OneToOne(mappedBy = "ticketBought")
	private Feedback feedback;

}