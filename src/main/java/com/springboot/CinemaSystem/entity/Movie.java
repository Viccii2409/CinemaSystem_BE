package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
	@Column(nullable = false)
	private String title;
	private int duration;
	private Date releaseDate;


	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	@Column(nullable = false)
	private boolean status;
	@Transient
	private float rating;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIdentityReference(alwaysAsId = true)
	private List<Showtime> showtime;

	@ManyToOne
	@JoinColumn(name = "directorID")
	@JsonIgnoreProperties("movie")	// Bỏ qua thuộc tính "movies" của "director" để tránh vòng lặp
	private Director director;

	@ManyToOne
	@JoinColumn(name = "languageID")
	@JsonIgnoreProperties("movie")
	private Language language;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("movie-trailer")
	private Trailer trailer;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> image;

	@ManyToMany
	@JoinTable(
			name = "movie_cast",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "castID")
	)
	@JsonIgnoreProperties("movie")
	private List<Cast> cast;

	@ManyToMany
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	@JsonIgnoreProperties("movie")
	private List<Genre> genre;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("movie")
	private List<Feedback> feedback;

}