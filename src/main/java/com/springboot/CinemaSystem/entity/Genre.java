package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genreID")
	private long ID;
	private String name;
	private String description;
	private boolean status;

	@ManyToMany(mappedBy = "genre")
	private List<Movie> movie;

	@ManyToMany(mappedBy = "genre")
	private List<Customer> customer;

}