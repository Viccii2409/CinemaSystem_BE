package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

	private boolean status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionID")
	@JsonIgnoreProperties("employee")
	private Position position;

}