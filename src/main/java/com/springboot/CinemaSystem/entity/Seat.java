package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatID")
	private long ID;
	private String name;
	private int seatNum;
	private String rowNum;
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "roomID")
	@JsonBackReference
	private Room room;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<SeatTicket> seatTicket;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<SeatAvailability> seatAvailability;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<SeatReservation> seatReservation;

	@ManyToOne
	@JoinColumn(name = "typeSeatID")
	@JsonManagedReference
	private TypeSeat typeSeat;

}