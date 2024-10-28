package com.springboot.CinemaSystem.entity;

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
	private Room room;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private List<SeatTicket> seatTicket;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private List<SeatAvailability> seatAvailability;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	private List<SeatReservation> seatReservation;

	@ManyToOne
	@JoinColumn(name = "typeSeatID")
	private TypeSeat typeSeat;

}