package com.springboot.cinema.service.impl;

import com.springboot.cinema.model.Movie;
import com.springboot.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Autowired
    public List<Movie> searchMovies(String title){
        return movieRepository.findByName(title);
    }



}
