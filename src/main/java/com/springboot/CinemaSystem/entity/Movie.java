package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.dto.GenreDto;
import com.springboot.CinemaSystem.dto.MovieDetailDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
	private LocalDate releaseDate;

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
	private Director director;

	@ManyToOne
	@JoinColumn(name = "languageID")
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

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("movie")
	private Slideshow slideshow;

	public long getId() {
		return ID;
	}

	public void setId(long ID) {
		this.ID = ID;
	}

	public MovieDetailDto toMovieDetailDto(){
		List<GenreDto> genreDtos = new ArrayList<>();
		List<FeedbackDto> feebackDtos = new ArrayList<>();
		for(Genre g : this.genre){
			genreDtos.add(g.toGenreDto());
		}
		for(Feedback f : this.feedback) {
			feebackDtos.add(f.toFeedbackDto());
		}
		return new MovieDetailDto(this.ID, this.title, this.duration, this.releaseDate,
				this.description, this.status, this.rating, this.director, this.language,
				this.trailer, this.image, this.cast, genreDtos, feebackDtos);
	}
}