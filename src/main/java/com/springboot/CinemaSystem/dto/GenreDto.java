package com.springboot.CinemaSystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreDto {
    public GenreDto(long ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    private long ID;
    private String name;
    private String description;
    private boolean status;
}
