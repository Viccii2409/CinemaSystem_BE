package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = SeatReservation.class)
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