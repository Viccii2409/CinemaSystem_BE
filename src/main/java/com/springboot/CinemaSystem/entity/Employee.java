package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Employee.class)
public class Employee extends User {

	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionID")
	private Position position;

}