package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {
	@Transient
	private int managedEmployees;

	@OneToOne
	@JoinColumn(name = "theaterID")
	private Theater theater;

	@OneToMany(mappedBy = "manager")
	private List<Agent> agents;

}