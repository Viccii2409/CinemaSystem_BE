package com.springboot.cinema.service.impl;

import com.springboot.cinema.model.Genre;
import com.springboot.cinema.repository.GenreRepository;
import com.springboot.cinema.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres(){
        return  genreRepository.findAll();
    }

    @Override
    public  List<Genre> searchGenres(String name){
        return  genreRepository.findByName(name);
    }

    @Override
    public Genre addGenre(Genre genre){
        return genreRepository.save(genre);
    }

    @Override
    public Genre updateGenre(Long id, Genre genreDetails){
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription());
        genre.setStatus(genreDetails.isStatus());
        return genreRepository.save(genre);
    }

    @Override
    public void hideGenre(Long id){
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
        genre.setStatus(!genre.isStatus());
        genreRepository.save(genre);
    }


}
