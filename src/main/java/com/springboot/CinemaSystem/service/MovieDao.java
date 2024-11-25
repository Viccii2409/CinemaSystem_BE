package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.*;

import java.util.Date;
import java.util.List;

public interface MovieDao {
	public boolean addMovie(Movie movie);
	public boolean editMovie(Long ID, Movie movie);
	public List<MovieDto> getCommingSoonMovie();
	public List<MovieDto> getShowingNowMovie();
	public boolean updateStatusMovie(int movieID);
	public Movie getMovieByID(int movieID);
	public List<Movie> getAllMovies();
	public List<MovieDto> getAllMovie();
	public List<Movie> searchMovies(String title);
	public Movie getMovieByID(long id);
	public void deleteMovie(long id);
	public List<MovieDto> searchMoviesByGenre(String genreName);


	public List<Movie> getMoviesByGenre(int genreID);
	public List<Movie> getMoviesByLanguage(int languageID);
	public List<Movie> searchMoviesByTitle(String title);
	public List<Movie> getMoviesByCastID(int castID);
	public List<Movie> getMoviesByDirectorID(int directorID);

	public Genre addGenre(Genre genre);
	public  List<Genre> searchGenres(String name);
	public Genre updateGenre(Long id, Genre genre);
	public void hideGenre(Long id);
	public boolean updateStatusGenre(long genreID);
	public Genre getGenreByID(long genreID);
	public List<Genre> getAllGenres();
	public void deleteGenre(Long id);
	public boolean deleteGenreMovie(int movieID, int genreID);

	public boolean addLanguage(Language language);
	public boolean updateLanguage(Language language);
	public Language getLanguageByID(int languageID);
	public List<Language> getAllLanguages();

	public boolean addTrailer(Trailer trailer);
	public boolean updateTrailer(Trailer trailer);
	public Trailer getTrailerByID(int trailerID);



	public Image getImage(int imageID);
	public boolean addImage(Image image);
	public boolean updateImage(Image image);

	public float getMovieStat(Date startDate, Date endDate);



}