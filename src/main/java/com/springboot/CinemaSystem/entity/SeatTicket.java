package com.springboot.CinemaSystem.entity;

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
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "basePriceID")
	private BasePrice basePrice;

	@ManyToOne
	@JoinColumn(name = "dayOfWeekID")
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "timeFrameID")
	private TimeFrame timeFrame;

	@ManyToOne
	@JoinColumn(name = "typeUserID")
	private TypeUser typeUser;

	@OneToOne(mappedBy = "seatTicket", cascade = CascadeType.ALL)
	private TicketBought ticketBought;

}