package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> searchMovies(String title);
    Movie addMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie);
    void changeStatus(Long id);
    public Movie getMovieByID(long id);

    public Genre addGenre(Genre genre);

    public Genre getGenre(long id);
}
