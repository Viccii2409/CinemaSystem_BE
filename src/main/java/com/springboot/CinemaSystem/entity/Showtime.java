package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.*;

@Data
@Entity
public class Showtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "showtimeID")
	private long ID;
	private Date date;
	private Time startTime;
	private Time endTime;
	private boolean status;

	@Transient
	private int availableSeats;		// số ghế được đặt

	@ManyToOne
	@JoinColumn(name = "roomID")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonManagedReference
	private Movie movie;

	@OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
	private List<TicketBought> ticketBought;

	@OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatAvailability> seatAvailability;

}