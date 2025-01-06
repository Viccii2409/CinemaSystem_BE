package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Genre;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreDto {
    private long ID;
    private String name;
    private String description;
    private int numberMovie;

    public GenreDto(long ID, String name, int numberMovie) {
        this.name = name;
        this.ID = ID;
        this.numberMovie = numberMovie;
    }

    public static GenreDto toGenreDto(Genre genre) {
        return new GenreDto(genre.getID(), genre.getName(), genre.getMovie().size());
    }
}
