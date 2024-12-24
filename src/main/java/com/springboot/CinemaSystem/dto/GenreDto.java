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

    public GenreDto(long ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public static GenreDto toGenreDto(Genre genre) {
        return new GenreDto(genre.getID(), genre.getName());
    }
}
