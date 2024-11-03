package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.entity.Theater;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public Movie getTheaterById(@PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id);
        if(movie != null ){
            return movie;
        }
        throw new NotFoundException("Theater not found with ID: " + id);
    }

    @PostMapping("/genre/add")
    public Genre addGenre(@RequestBody Genre genre) {
        System.out.println(genre);
        return movieService.addGenre(genre);
    }

    @GetMapping("/genre/{id}")
    public Genre getGenre(@PathVariable("id") long id) {
        System.out.println(id);
        return movieService.getGenre(id);
    }
}
