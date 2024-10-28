package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatReservationID")
	private long ID;
	private String reservationTime;
	private String expiryTime;

	@ManyToOne
	@JoinColumn(name = "userID")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "seatID")
	private Seat seat;

}