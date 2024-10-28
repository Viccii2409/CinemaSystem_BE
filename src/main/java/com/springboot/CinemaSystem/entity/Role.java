package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleID")
	private long ID;
	private String name;

	@ManyToMany(mappedBy = "role")
	private List<Position> position;

}