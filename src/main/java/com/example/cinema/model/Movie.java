package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieID")
    private Long id;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Duration", nullable = false)
    private int duration;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Rating")
    private float rating;

    // Quan hệ với bảng Movie_Genre

    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "movie")
    private List<Feedback> feedbacks;
    // Quan hệ với bảng Showtime
    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;

    @OneToMany(mappedBy = "movie")
    private List<Image> images;
    // Các getter và setter

    @OneToMany(mappedBy = "movie")
    private List<MovieCast> movieCasts;

    @OneToOne(mappedBy = "movie")
    private Trailer trailer;

    @ManyToOne
    @JoinColumn(name = "DirectorID", nullable = false)
    private Director director;

    @ManyToOne
    @JoinColumn(name = "LanguageID", nullable = false)
    private Language language;


}
