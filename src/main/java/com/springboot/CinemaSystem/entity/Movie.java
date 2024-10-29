package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieID")
	private long ID;
	private String title;
	private int duration;
	private Date releaseDate;
	private String description;
	private boolean status;
	@Transient
	private float rating;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<Showtime> showtime;

	@ManyToOne
	@JoinColumn(name = "directorID")
	@JsonManagedReference
	private Director director;

	@ManyToOne
	@JoinColumn(name = "languageID")
	@JsonManagedReference
	private Language language;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Trailer trailer;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Image> image;

	@ManyToMany
	@JoinTable(
			name = "movie_cast",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "castID")
	)
	@JsonManagedReference
	private List<Cast> cast;

	@ManyToMany
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	@JsonManagedReference
	private List<Genre> genre;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Feedback> feedback;

}