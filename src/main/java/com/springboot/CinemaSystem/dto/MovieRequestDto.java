package com.springboot.CinemaSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.CinemaSystem.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    private String title;
    private int duration;
    private LocalDate releaseDate;
    private String description;
    private String director;
    private String cast;
    private Language language;
    private String trailer;
    private String image;
    private List<Genre> genre;
}
