package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "languageID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "language")
	@JsonIgnore
	private List<Movie> movie;



	// Default no-argument constructor
	public Language() {
	}

	// Constructor with parameters (if needed)
	public Language(String name) {
		this.name = name;
	}

	// Getters and Setters
	public Long getId() {
		return ID;
	}

	public void setId(Long id) {
		this.ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}