package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.dto.MovieShowtimeDto;
import com.springboot.CinemaSystem.dto.ShowtimeMovieDto;
import com.springboot.CinemaSystem.dto.ShowtimeTheaterIDDto;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.GenreRepository;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieDaoImpl implements MovieDao {

	private GenreRepository genreRepository;
	private MovieRepository movieRepository;

	@Autowired
	public MovieDaoImpl(GenreRepository genreRepository, MovieRepository movieRepository) {
		this.genreRepository = genreRepository;
		this.movieRepository = movieRepository;
	}


	@Override
	public boolean addMovie(Movie movie) {
		return false;
	}

	@Override
	public boolean editMovie(Movie movie) {
		return false;
	}

	@Override
	public List<MovieDto> getCommingSoonMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findAll().stream()
				.filter(movie -> movie.getReleaseDate().isAfter(today))
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<MovieDto> getShowingNowMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findAll().stream()
				.filter(movie -> movie.getReleaseDate().isBefore(today) || movie.getReleaseDate().isEqual(today))
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}


	private MovieDto convertToDto(Movie movie) {
		// Kiểm tra danh sách image
		String link = (movie.getImage() == null || movie.getImage().isEmpty()) ? null : movie.getImage().get(0).getLink();

		return new MovieDto(
				movie.getId(),
				movie.getTitle(),
				link,
				movie.getShowtime()
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
	};


	@Override
	public boolean updateStatusMovie(int movieID) {
		return false;
	}

	@Override
	public Movie getMovieByID(int movieID) {
		return null;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public List<Movie> searchMovies(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	public Movie getMovieByID(long id) {
		return movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("khong tim thay"));
	}

	@Override
	public List<Movie> getMoviesByGenre(int genreID) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByLanguage(int languageID) {
		return List.of();
	}

	@Override
	public List<Movie> searchMoviesByTitle(String title) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByCastID(int castID) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByDirectorID(int directorID) {
		return List.of();
	}

	@Override
	public Genre addGenre(Genre genre) {
		return genreRepository.save(genre);
	}

	@Override
	public List<Genre> searchGenres(String name) {
		return  genreRepository.findByName(name);
	}

	@Override
	public Genre updateGenre(Long id, Genre genreDetails) {
		Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
		genre.setName(genreDetails.getName());
		genre.setDescription(genreDetails.getDescription());
		genre.setStatus(genreDetails.isStatus());
		return genreRepository.save(genre);
	}

	@Override
	public void hideGenre(Long id) {
		Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
		genre.setStatus(!genre.isStatus());
		genreRepository.save(genre);
	}

	@Override
	public boolean updateStatusGenre(long genreID) {
		return false;
	}

	@Override
	public Genre getGenreByID(long genreID) {
		return genreRepository.findById(genreID).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
	}

	@Override
	public List<Genre> getAllGenres() {
		return  genreRepository.findAll();
	}

	@Override
	public boolean deleteGenreMovie(int movieID, int genreID) {
		return false;
	}

	@Override
	public boolean addLanguage(Language language) {
		return false;
	}

	@Override
	public boolean updateLanguage(Language language) {
		return false;
	}

	@Override
	public Language getLanguageByID(int languageID) {
		return null;
	}

	@Override
	public List<Language> getAllLanguages() {
		return List.of();
	}

	@Override
	public boolean addTrailer(Trailer trailer) {
		return false;
	}

	@Override
	public boolean updateTrailer(Trailer trailer) {
		return false;
	}

	@Override
	public Trailer getTrailerByID(int trailerID) {
		return null;
	}

	@Override
	public boolean addCast(Cast cast) {
		return false;
	}

	@Override
	public boolean deleteCast(Cast cast) {
		return false;
	}

	@Override
	public boolean updateCast(Cast cast) {
		return false;
	}

	@Override
	public Cast getCastByID(int castID) {
		return null;
	}

	@Override
	public List<Cast> getAllCasts() {
		return List.of();
	}

	@Override
	public List<Cast> getCastsByMovieID(int movieID) {
		return List.of();
	}

	@Override
	public List<Cast> searchCastByName(String name) {
		return List.of();
	}

	@Override
	public boolean addDirector(Director director) {
		return false;
	}

	@Override
	public boolean updateDirector(Director director) {
		return false;
	}

	@Override
	public Director getDirectorByID(int directorID) {
		return null;
	}

	@Override
	public List<Director> getAllDirectors() {
		return List.of();
	}

	@Override
	public Director getDirectorsByMovieID(int movieID) {
		return null;
	}

	@Override
	public List<Director> searchDirectorByName(String name) {
		return List.of();
	}

	@Override
	public Image getImage(int imageID) {
		return null;
	}

	@Override
	public boolean addImage(Image image) {
		return false;
	}

	@Override
	public boolean updateImage(Image image) {
		return false;
	}

	@Override
	public float getMovieStat(Date startDate, Date endDate) {
		return 0;
	}

}