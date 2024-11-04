package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.GenreRepository;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

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

    @Override
    public Genre addGenre(Genre genre) {
        try {
            return genreRepository.save(genre);
        } catch (Exception e) {
            throw new DataProcessingException(e.getMessage());
        }
    }

    @Override
    public Genre getGenre(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found id"));
    }

    public List<MovieDto> getShowingNowMovie() {
        LocalDate today = LocalDate.now();
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getReleaseDate().isBefore(today) || movie.getReleaseDate().isEqual(today))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getCommingSoonMovie() {
        LocalDate today = LocalDate.now();
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getReleaseDate().isAfter(today))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private MovieDto convertToDto(Movie movie) {
        String  Link = movie.getImage().isEmpty() ? null : movie.getImage().get(0).getLink();
        return new MovieDto(movie.getId(), movie.getTitle(), Link);
    }
}
