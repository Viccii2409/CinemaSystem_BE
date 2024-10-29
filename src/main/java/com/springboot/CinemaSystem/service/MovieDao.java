package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.*;

import java.util.Date;
import java.util.List;

public interface MovieDao {
	public boolean addMovie(Movie movie);
	public boolean editMovie(Movie movie);
	public List<Movie> getCommingSoonMovie();
	public List<Movie> getShowingNowMovie();
	public boolean updateStatusMovie(int movieID);
	public Movie getMovieByID(int movieID);
	public List<Movie> getAllMovies();
	public List<Movie> getMoviesByGenre(int genreID);
	public List<Movie> getMoviesByLanguage(int languageID);
	public List<Movie> searchMoviesByTitle(String title);
	public List<Movie> getMoviesByCastID(int castID);
	public List<Movie> getMoviesByDirectorID(int directorID);

	public boolean addGenre(Genre genre);
	public boolean updateGenre(Genre genre);
	public boolean updateStatusGenre(int genreID);
	public Genre getGenreByID(int genreID);
	public List<Genre> getAllGenres();
	public boolean deleteGenreMovie(int movieID, int genreID);

	public boolean addLanguage(Language language);
	public boolean updateLanguage(Language language);
	public Language getLanguageByID(int languageID);
	public List<Language> getAllLanguages();

	public boolean addTrailer(Trailer trailer);
	public boolean updateTrailer(Trailer trailer);
	public Trailer getTrailerByID(int trailerID);

	public boolean addCast(Cast cast);
	public boolean deleteCast(Cast cast);
	public boolean updateCast(Cast cast);
	public Cast getCastByID(int castID);
	public List<Cast> getAllCasts();
	public List<Cast> getCastsByMovieID(int movieID);
	public List<Cast> searchCastByName(String name);

	public boolean addDirector(Director director);
	public boolean updateDirector(Director director);
	public Director getDirectorByID(int directorID);
	public List<Director> getAllDirectors();
	public Director getDirectorsByMovieID(int movieID);
	public List<Director> searchDirectorByName(String name);

	public Image getImage(int imageID);
	public boolean addImage(Image image);
	public boolean updateImage(Image image);

	public float getMovieStat(Date startDate, Date endDate);


}