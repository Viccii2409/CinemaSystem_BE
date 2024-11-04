package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlideshowRepository extends JpaRepository<Slideshow, Long> {
    @Query("SELECT s.url FROM Slideshow s")
    List<String> findAllSlideshowUrl();
}
