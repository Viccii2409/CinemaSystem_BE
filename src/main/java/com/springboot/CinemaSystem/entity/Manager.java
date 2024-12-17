package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {
	@OneToOne
	@JoinColumn(name = "theaterID")
	private Theater theater;

	@OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST)
	private List<Agent> agents;

}