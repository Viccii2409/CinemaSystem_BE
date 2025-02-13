package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.*;
import jakarta.persistence.*;
import lombok.*;

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

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "trailer", nullable = false)
	private String trailer;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Showtime> showtime = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "languageID")
	private Language language;


	@ManyToMany
	@JoinTable(
			name = "movie_genre",
			joinColumns = @JoinColumn(name = "movieID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	@JsonIgnoreProperties("movie") // Bỏ qua trường movie trong Genre
	private List<Genre> genre = new ArrayList<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("movie") // Bỏ qua trường movie trong Feedback
	private List<Feedback> feedback;

	@OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("movie")
	private Slideshow slideshow;

	public static Movie toMovie(MovieRequestDto movieRequestDto) {
		Movie movie = new Movie();
		movie.setId(movieRequestDto.getId());
		movie.setTitle(movieRequestDto.getTitle());
		movie.setDuration(movieRequestDto.getDuration());
		movie.setReleaseDate(movieRequestDto.getReleaseDate());
		movie.setDescription(movieRequestDto.getDescription());
		movie.setDirector(movieRequestDto.getDirector());
		movie.setCast(movieRequestDto.getCast());
		Language language1 = new Language();
		language1.setId(movieRequestDto.getLanguageID());
		movie.setLanguage(language1);
		for(Long id : movieRequestDto.getGenreID()) {
			Genre genre1 = new Genre();
			genre1.setID(id);
			movie.getGenre().add(genre1);
		}
		movie.setStatus(true);
		return movie;
	}

	public long getId() {
		return ID;
	}

	public void setId(long ID) {
		this.ID = ID;
	}

	public MovieDetailAdminDto toMovieDetailAdminDto(){
		List<GenreDto> genreDtos = new ArrayList<>();
		List<FeedbackDto> feebackDtos = new ArrayList<>();
		for(Genre g : this.genre){
			genreDtos.add(g.toGenreDto());
		}
		for(Feedback f : this.feedback) {
			feebackDtos.add(FeedbackDto.toFeedbackDto(f));
		}
		return new MovieDetailAdminDto(
				this.ID,
				this.title,
				this.duration,
				this.releaseDate,
				this.description,
				this.status,
				this.calculateAverageRating(), // Gọi phương thức tính rating
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
		dto.setImage(this.getImage());
		dto.setReleaseDate(this.getReleaseDate());
		dto.setStatus(this.isStatus());
		dto.setCountShowtime(this.getShowtime().size());
		List<GenreDto> genreDtos = this.getGenre().stream()
				.map(Genre::toGenreDto)
				.collect(Collectors.toList());
		dto.setGenre(genreDtos);
		dto.setShowtime(
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
				.collect(Collectors.toList())
		);
		return dto;
	}


	@Override
	public String toString() {
		return "Movie{id=" + ID + ", title='" + title + "', releaseDate=" + releaseDate + ", status=" + status + "}";
	}


	public MovieShowtimeDto toMovieShowtimeDto() {
		return new MovieShowtimeDto(this.ID, this.title, this.duration, this.description, this.image);
	}

	public String getFirstImage() {
		return this.image;
	}

	// Tính rating phim
	public float calculateAverageRating() {
		if (this.feedback == null || this.feedback.isEmpty()) {
			return 0; // Nếu không có feedback nào, trả về 0.
		}

		double averageRating = this.feedback.stream()
				.map(Feedback::getStar)
				.filter(Objects::nonNull)
				.mapToInt(Integer::intValue) // Chuyển đổi từ Integer sang int
				.average()
				.orElse(0); // Nếu không có giá trị hợp lệ, trả về 0.

		return (float) averageRating;
	}


    public void setGenres(List<Genre> genres) {
        this.genre = genres;
    }

}

