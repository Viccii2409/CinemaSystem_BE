package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
	@JsonBackReference(value = "theater-agent")
	private Theater theater;

	@OneToMany(mappedBy = "agent")
	private List<Payment> payment;

}




