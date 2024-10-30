package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@JsonBackReference
	private List<Movie> movie;

	@ManyToMany(mappedBy = "genre")
	@JsonBackReference
	private List<Customer> customer;

}