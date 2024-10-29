package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionID")
	@JsonManagedReference
	private Position position;

}