package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Time startTime;
	private Time endTime;
	@Column(nullable = false)
	private boolean status;

	@Transient
	private int availableSeats;		// số ghế được đặt

	@ManyToOne
	@JoinColumn(name = "roomID", nullable = false)
	@JsonIgnoreProperties("showtime")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "movieID")
	private Movie movie;

	@OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
	private List<TicketBought> ticketBought;

	@OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatAvailability> seatAvailability;

}