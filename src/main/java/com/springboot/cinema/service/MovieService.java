package com.springboot.cinema.service;

import com.springboot.cinema.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> searchMovies(String title);
    Movie addMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie);
    void changeStatus(Long id);
}
