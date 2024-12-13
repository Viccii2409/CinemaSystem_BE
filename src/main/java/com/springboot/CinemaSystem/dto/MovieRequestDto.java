package com.springboot.CinemaSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)  // Thêm annotation này

public class MovieRequestDto {
    private String title;
    private int duration;
    //    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate releaseDate;
    private String releaseDate;
    private String description;
    private String director;
    private String cast;
    private Language language;
    //    private String trailer;
//    private String image;
//    private List<Genre> genre;
    private List<GenreDto> genre;

    public LocalDate getReleaseDate() {
        return LocalDate.parse(releaseDate);
    }
}