package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = SeatAvailability.class)
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