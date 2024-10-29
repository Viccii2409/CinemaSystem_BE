package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

	private int points;

	@ManyToOne
	@JoinColumn(name = "levelID")
	private Level level;

	@ManyToMany
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketBought> ticketBought;

	@ManyToMany
	@JoinTable(
			name = "user_discount",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "discountID")
	)
	private List<Discount> discount;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatReservation> seatReservation;

}