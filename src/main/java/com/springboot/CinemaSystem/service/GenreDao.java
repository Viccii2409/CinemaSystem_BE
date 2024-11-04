package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAllGenres();
    List<Genre> searchGenres(String name);
    Genre addGenre(Genre genre);
    Genre updateGenre(Long id, Genre genre);
    void hideGenre(Long id);
}
