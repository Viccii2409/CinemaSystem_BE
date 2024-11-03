package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.Movie;

import java.util.List;

public interface MovieService {
   public List<Movie> getAllMovies();
    List<Movie> searchMovies(String title);
    Movie addMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie);
    void changeStatus(Long id);
    public Movie getMovieByID(long id);
    public List<MovieDto> getShowingNowMovie();
    public List<MovieDto> getCommingSoonMovie();
}
