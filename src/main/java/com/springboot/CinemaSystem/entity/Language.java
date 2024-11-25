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

}