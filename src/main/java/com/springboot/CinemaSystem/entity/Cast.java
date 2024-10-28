package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Cast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "castID")
	private int ID;
	private String name;

	@ManyToMany(mappedBy = "cast")
	private List<Movie> movie;

}