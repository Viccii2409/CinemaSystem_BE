package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatTicketID")
	private long ID;

	@Transient
	private float price;

	@Transient
	private float surcharge;

	@Transient
	private float finalPrice;

	@ManyToOne
	@JoinColumn(name = "seatID")
	@JsonManagedReference
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "basePriceID")
	@JsonManagedReference
	private BasePrice basePrice;

	@ManyToOne
	@JoinColumn(name = "dayOfWeekID")
	@JsonManagedReference
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "timeFrameID")
	@JsonManagedReference
	private TimeFrame timeFrame;

	@ManyToOne
	@JoinColumn(name = "typeUserID")
	@JsonManagedReference
	private TypeUser typeUser;

	@OneToOne(mappedBy = "seatTicket", cascade = CascadeType.ALL)
	@JsonBackReference
	private TicketBought ticketBought;

}