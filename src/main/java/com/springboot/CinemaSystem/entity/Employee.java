package com.springboot.CinemaSystem.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionID")
	private Position position;

}