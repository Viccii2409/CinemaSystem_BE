package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.*;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"showtime", "feedback", "genre", "image"})
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
	private String director;

	@Column(name ="cast", columnDefinition = "TEXT")
	private String cast;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Showtime> showtime;

	@ManyToOne
	@JoinColumn(name = "languageID")
	private Language language;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("movie-trailer")
	private Trailer trailer;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Image> image;


	@ManyToMany
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	@JsonIgnoreProperties("movie") // Bỏ qua trường movie trong Genre
	private List<Genre> genre;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("movie") // Bỏ qua trường movie trong Feedback
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
		return new MovieDetailDto(
				this.ID,
				this.title,
				this.duration,
				this.releaseDate,
				this.description,
				this.status,
				this.rating,
				this.director,
				this.language,
				this.trailer,
				this.image,
				this.cast,
				genreDtos,
				feebackDtos,
				this.getShowtime()
				.stream()
				.filter(showtime -> showtime.getRoom().isStatus() == true && ("upcoming".equals(showtime.getAction()) || "running".equals(showtime.getAction())))
				.map(showtime -> new ShowtimeTheaterIDDto(
						showtime.getID(),
						showtime.getDate(),
						showtime.getStartTime(),
						showtime.getEndTime(),
						showtime.getAction(),
						showtime.getRoom().getTheater().getID(),
						showtime.getRoom().getSeat().size() - showtime.getAvailableSeats(),
						showtime.getRoom().getTypeRoom().getName()
				))
				.collect(Collectors.toList()));
	}

	public MovieDto toMovieDto() {
		MovieDto dto = new MovieDto();
		dto.setId(this.getId());
		dto.setTitle(this.getTitle());

		// Chuyển đổi các thể loại của phim thành danh sách GenreDto
		List<GenreDto> genreDtos = this.getGenre().stream()
				.map(Genre::toGenreDto)
				.collect(Collectors.toList());
		dto.setGenres(genreDtos);

		// Chuyển đổi các thuộc tính khác nếu cần
		return dto;
	}


	@Override
	public String toString() {
		return "Movie{id=" + ID + ", title='" + title + "', releaseDate=" + releaseDate + ", status=" + status + "}";
	}

	// Tính rating phim




	public MovieShowtimeDto toMovieShowtimeDto() {
		return new MovieShowtimeDto(this.ID, this.title, this.duration, this.description, this.image.get(0).getLink());
	}

	public String getFirstImage() {
		return this.image.get(0).getLink();
	}
}