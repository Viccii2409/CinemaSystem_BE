package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.MovieDao;

import java.util.Date;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

	@Override
	public boolean addMovie(Movie movie) {
		return false;
	}

	@Override
	public boolean editMovie(Movie movie) {
		return false;
	}

	@Override
	public List<Movie> getCommingSoonMovie() {
		return List.of();
	}

	@Override
	public List<Movie> getShowingNowMovie() {
		return List.of();
	}

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
		return List.of();
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
	public boolean addGenre(Genre genre) {
		return false;
	}

	@Override
	public boolean updateGenre(Genre genre) {
		return false;
	}

	@Override
	public boolean updateStatusGenre(int genreID) {
		return false;
	}

	@Override
	public Genre getGenreByID(int genreID) {
		return null;
	}

	@Override
	public List<Genre> getAllGenres() {
		return List.of();
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