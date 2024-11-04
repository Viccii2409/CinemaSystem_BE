package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Position.class)
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	private String name;
	private boolean status;

	@OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
	private List<Employee> employee;

	@ManyToMany
	@JoinTable(
			name = "position_role",
			joinColumns = @JoinColumn(name = "positionID"),
			inverseJoinColumns = @JoinColumn(name = "roleID")
	)
	private List<Role> role;


}