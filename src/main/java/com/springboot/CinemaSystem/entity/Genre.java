package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.GenreDto;
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

	@Lob
	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	private String description;
	private boolean status;

	@ManyToMany(mappedBy = "genre")
	@JsonIgnoreProperties("genre")
	private List<Movie> movie;

	@ManyToMany(mappedBy = "genre")
	@JsonIgnoreProperties("genre")
	private List<Customer> customer;

	public GenreDto toGenreDto(){
		return new GenreDto(this.ID, this.name);
	}

}