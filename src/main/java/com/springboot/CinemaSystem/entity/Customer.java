package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JsonManagedReference
	private Level level;

	@ManyToMany
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	@JsonManagedReference
	private List<Genre> genre;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TicketBought> ticketBought;

	@ManyToMany
	@JoinTable(
			name = "user_discount",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "discountID")
	)
	@JsonManagedReference
	private List<Discount> discount;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SeatReservation> seatReservation;

}