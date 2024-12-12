package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
@Data
public class ShowtimeDto {
    private long ID;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String action;
    private MovieShowtimeDto movie;
    private boolean status;
    // Constructor cho câu truy vấn JPQL
    public ShowtimeDto(long ID, Date date, Time startTime, Time endTime, String action, MovieShowtimeDto movie, boolean status) {
        this.ID = ID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.action = action;
        this.movie = movie;
        this.status = status;
    }

    // Getter và Setter cho các thuộc tính
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MovieShowtimeDto getMovie() {
        return movie;
    }

    public void setMovie(MovieShowtimeDto movie) {
        this.movie = movie;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStautus(boolean status){
        this.status = status;
    }
}
