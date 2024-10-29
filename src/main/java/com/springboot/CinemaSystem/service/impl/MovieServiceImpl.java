package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String title){
        return movieRepository.findByTitle(title);
    }

    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        return null;
    }

    @Override
    public void changeStatus(Long id) {

    }

    @Override
    public Movie getMovieByID(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("khong tim thay"));
    }


}
