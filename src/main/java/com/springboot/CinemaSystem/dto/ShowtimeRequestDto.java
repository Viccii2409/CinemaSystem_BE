package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Movie;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeRequestDto {
    private Long theaterId;
    private long movieId;
    private long roomId;
    private Date date;
    private Time startTime;
//    private boolean status;

    public LocalTime getEndTime(Movie movie) {
        // Chuyển startTime (java.sql.Time) sang LocalTime
        LocalTime localStartTime = startTime.toLocalTime();

        // Tính endTime: startTime cộng với duration của movie
        int durationInMinutes = (int) movie.getDuration();

        // Cộng thêm duration vào startTime
        return localStartTime.plusMinutes(durationInMinutes);
    }
}