package com.springboot.CinemaSystem.entity;

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
	private List<Showtime> showtime;

	@ManyToOne
	@JoinColumn(name = "directorID")
	private Director director;

	@ManyToOne
	@JoinColumn(name = "languageID")
	private Language language;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Trailer trailer;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> image;

	@ManyToMany
	@JoinTable(
			name = "movie_cast",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "castID")
	)
	private List<Cast> cast;

	@ManyToMany
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feedback> feedback;

}