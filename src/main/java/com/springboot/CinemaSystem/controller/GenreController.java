package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.entity.Genre;
import com.springboot.CinemaSystem.service.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreDao genreDao;

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreDao.getAllGenres();
    }

    @GetMapping("/search")
    public List<Genre> searchGenres(@RequestParam("name") String name){
        return genreDao.searchGenres(name);
    }

    @PostMapping
    public Genre addGenre(@RequestBody Genre genre){
        return genreDao.addGenre(genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails){
        return genreDao.updateGenre(id, genreDetails);
    }

    @PutMapping("/{id}/hide")
    public ResponseEntity<String> hideGenre(@PathVariable Long id) {
        genreDao.hideGenre(id);
        return ResponseEntity.ok("Chuyển trạng thái thành công");

    }
}
