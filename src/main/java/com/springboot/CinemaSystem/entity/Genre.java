package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
<<<<<<< HEAD
=======
import com.springboot.CinemaSystem.dto.GenreDto;
>>>>>>> main
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	@JsonIgnore
	private List<Movie> movie;

	@ManyToMany(mappedBy = "genre")
	@JsonIgnore
	private List<Customer> customer;

	public GenreDto toGenreDto(){
		return new GenreDto(this.ID, this.name, this.description, this.status);
	}

}