package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
        private Long id;
        private String title;
        private String link;
        private List<ShowtimeTheaterIDDto> showtime;

}

