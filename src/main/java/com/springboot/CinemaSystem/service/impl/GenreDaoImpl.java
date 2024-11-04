package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.repository.GenreRepository;
import com.springboot.CinemaSystem.service.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreDaoImpl implements GenreDao {
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
