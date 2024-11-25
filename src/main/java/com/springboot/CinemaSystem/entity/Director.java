package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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

	@OneToMany(mappedBy = "director")
	@JsonIgnore
	private List<Movie> movie;

}