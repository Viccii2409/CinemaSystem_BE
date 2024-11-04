package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.entity.Slideshow;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.DiscountDao;
import com.springboot.CinemaSystem.service.MovieService;
import com.springboot.CinemaSystem.service.SlideshowDao;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    private TheaterDao theaterDao;
    @Autowired
    private SlideshowDao slideshowDao;
    @GetMapping("/getAll")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id);
        if(movie != null ){
            return movie;
        }
        throw new NotFoundException("Movie not found with ID: " + id);
    }
    @GetMapping("/showingNow")
    public List<MovieDto> getShowingNowMovie(){
        return movieService.getShowingNowMovie();
    }
    @GetMapping("/comingSoon")
    public List<MovieDto> getCommingSoonMovie(){
        return movieService.getCommingSoonMovie();
    }
    @GetMapping("/discount")
    public List<Discount> getAllDiscounts(){
        return discountDao.getAllDiscounts();
    }
    @GetMapping("/slideshow")
    public List<Slideshow> getAllSlideshow(){
        return slideshowDao.getAllSlideshow();
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
