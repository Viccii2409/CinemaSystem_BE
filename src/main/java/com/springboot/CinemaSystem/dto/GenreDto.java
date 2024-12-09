package com.springboot.CinemaSystem.dto;

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
    private boolean status;

    public GenreDto(long id, String name, String description) {
        this.ID=ID;
        this.name=name;
        this.description=description;
    }

    public GenreDto(long ID) {
        this.ID=ID;
    }
}
