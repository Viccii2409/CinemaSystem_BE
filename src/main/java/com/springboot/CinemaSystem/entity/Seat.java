package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.SeatDto;
import com.springboot.CinemaSystem.dto.TypeSeatDto;
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
	@Column
	private String name;
	@Column(nullable = false)
	private int seatNum;
	@Column(nullable = false)
	private int rowNum;
	@Column(nullable = false)
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "typeSeatID", nullable = false)
	private TypeSeat typeSeat;


	public void updateFrom(Seat other) {
		this.name = other.name;
		this.seatNum = other.seatNum;
		this.rowNum = other.rowNum;
		this.status = other.status;
		this.typeSeat = other.typeSeat;
	}

	@ManyToOne
	@JoinColumn(name = "roomID")
	@JsonBackReference(value = "room-seat")
	private Room room;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonIdentityReference(alwaysAsId = true)
	private List<SeatTicket> seatTicket;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonIdentityReference(alwaysAsId = true)
	private List<SeatAvailability> seatAvailability;

	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
	@JsonIdentityReference(alwaysAsId = true)
	private List<SeatReservation> seatReservation;

	public SeatDto toSeatDto() {
		return new SeatDto(this.ID, this.name, this.seatNum, this.rowNum, this.status, this.typeSeat.toTypeSeatDto());
	}

}