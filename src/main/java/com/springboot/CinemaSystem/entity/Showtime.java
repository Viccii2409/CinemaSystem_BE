package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Time;
import java.util.*;
import java.sql.Date;

@Data
@Entity
@ToString(exclude = {"room", "movie", "ticketBought", "seatAvailability"})
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

	@Override
	public String toString() {
		return "Showtime{id=" + ID + ", date=" + date + ", startTime=" + startTime + ", status=" + status + "}";
	}
}