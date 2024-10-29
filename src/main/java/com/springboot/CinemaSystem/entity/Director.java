package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Director {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "directorID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
	private List<Movie> movie;

}