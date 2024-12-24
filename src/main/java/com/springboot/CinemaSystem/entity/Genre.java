package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.GenreDto;
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
	private Long ID;
	private String name;

	@Lob
	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	private String description;

	@ManyToMany(mappedBy = "genre")
	@JsonIgnore
	private List<Movie> movie;

	@ManyToMany(mappedBy = "genre")
	@JsonIgnore
	private List<Customer> customer;

	public Genre(Long ID, String name, String description) {
		this.ID=ID;
		this.name=name;
		this.description=description;
	}

	public GenreDto toGenreDto(){
		return new GenreDto(this.ID, this.name, this.description);
	}

	// Constructor cần thiết cho Jackson để chuyển đổi từ ID (số)
	@JsonCreator
	public Genre(@JsonProperty("id") Long id) {
		this.ID = id;
	}
}