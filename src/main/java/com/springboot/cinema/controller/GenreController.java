package com.springboot.cinema.controller;

import com.springboot.cinema.model.Genre;
import com.springboot.cinema.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping("/search")
    public List<Genre> searchGenres(@RequestParam("name") String name){
        return genreService.searchGenres(name);
    }

    @PostMapping
    public Genre addGenre(@RequestBody Genre genre){
        return genreService.addGenre(genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails){
        return genreService.updateGenre(id, genreDetails);
    }

    @PutMapping("/{id}/hide")
    public ResponseEntity<String> hideGenre(@PathVariable Long id) {
        genreService.hideGenre(id);
        return ResponseEntity.ok("Chuyển trạng thái thành công");

    }
}
