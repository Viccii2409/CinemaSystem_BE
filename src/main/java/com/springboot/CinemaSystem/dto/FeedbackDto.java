package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private long ID;
    private String text;
    private String date;
    private Rating rating;
}
