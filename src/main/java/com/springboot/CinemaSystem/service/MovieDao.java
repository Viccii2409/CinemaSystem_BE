package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.*;

import java.util.Date;
import java.util.List;

public interface MovieDao {
	Movie addMovie(Movie movie);
	Movie updateMovie(Movie movie);
	public List<MovieDto> getCommingSoonMovie();
	public List<MovieDto> getShowingNowMovie();
	public List<Slideshow> getAllSlideshow();
	public boolean updateStatusMovie(int movieID);
	public Movie getMovieByID(int movieID);
	public List<Movie> getAllMovie();
	public List<Movie> searchMovies(String title);
	public Movie getMovieByID(long id);
	public void deleteMovie(long id);
	public List<MovieDto> searchMoviesByGenre(String genreName);
	public Movie getMovieDetails(long movieID);

	public List<Genre> customerGenre(Long customerID);
	public List<MovieDto> recommendMovies(List<Long> genreIds);

	public Genre addGenre(Genre genre);
	public  List<Genre> searchGenres(String name);
	public Genre updateGenre(Long id, Genre genre);
	public void hideGenre(Long id);
	public Genre getGenreByID(long genreID);
	public List<Genre> getAllGenres();
	public void deleteGenre(Long id);


	public Feedback addFeedback(Feedback feedback);
	public List<FeedbackDto> getFeedbackByMovie(long movieID);

}