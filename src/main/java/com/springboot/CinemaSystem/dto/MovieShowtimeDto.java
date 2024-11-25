package com.springboot.CinemaSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieShowtimeDto {
    private long id;
    private String title;
    private int duration;
    private String description;
    private String image;
    private List<ShowtimeMovieDto> showtime;

    public MovieShowtimeDto(long id, String title, int duration, String description, String image) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.image = image;
    }
}
