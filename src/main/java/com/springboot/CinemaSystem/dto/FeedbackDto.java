package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private long ID;
    private String text;
    private LocalDateTime date;
    private Rating rating;
}
