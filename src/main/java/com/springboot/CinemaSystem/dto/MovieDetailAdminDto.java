package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailAdminDto {
    private long ID;
    private String title;
    private int duration;
    private LocalDate releaseDate;
    private String description;
    private boolean status;
    private float rating;
    private String director;
    private Language language;
    private String trailer;
    private String image;
    private String cast;
    private List<GenreDto> genre;
    private List<FeedbackDto> feedback;
    private List<ShowtimeTheaterIDDto> showtime;
}
