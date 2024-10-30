package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JsonBackReference
	private Showtime showtime;

	@ManyToOne
	@JoinColumn(name = "seatID")
	@JsonManagedReference
	private Seat seat;

}