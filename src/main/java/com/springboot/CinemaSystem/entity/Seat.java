package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.SeatDto;
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
	@Column(name = "row_num", nullable = false)
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

	@OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Ticket> ticket;

	public SeatDto toSeatDto() {
		return new SeatDto(this.getID(), this.getName(), this.seatNum, this.rowNum, this.status, this.typeSeat.toTypeSeatDto());
	}

	@OneToMany(mappedBy = "seat", fetch = FetchType.LAZY)
	private List<SelectedSeat> selectedSeats;

}