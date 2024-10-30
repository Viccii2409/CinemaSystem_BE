package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
	@JsonManagedReference
	private Theater theater;

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Payment> payment;

}




