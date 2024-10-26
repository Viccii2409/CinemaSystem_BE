package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MovieGenre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieGenreID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "GenreID",nullable = false)
    private Genre genre;


}
