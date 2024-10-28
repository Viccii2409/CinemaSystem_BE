package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("AGENT")
public class Agent extends Employee {
	@Transient
	private int ticketsSold;

	@ManyToOne
	@JoinColumn(name = "theaterID")
	private Theater theater;

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	private List<Payment> payment;

}




