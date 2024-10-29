package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JsonBackReference
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "seatID")
	@JsonManagedReference
	private Seat seat;

}