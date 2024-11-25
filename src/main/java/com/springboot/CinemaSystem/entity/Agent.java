package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@DiscriminatorValue("AGENT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Agent.class)
public class Agent extends Employee {
	@Transient
	private int ticketsSold;

	@ManyToOne
	@JoinColumn(name = "theaterID")
	private Theater theater;

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	private List<Payment> payment;

}




