package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
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