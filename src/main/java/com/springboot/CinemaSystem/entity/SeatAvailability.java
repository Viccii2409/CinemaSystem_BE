package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatAvailabilityID")
	private long ID;
	private boolean isAvailable;

	@ManyToOne
	@JoinColumn(name = "showtimeID")
	private Showtime showtime;

	@ManyToOne
	@JoinColumn(name = "seatID")
	private Seat seat;

}