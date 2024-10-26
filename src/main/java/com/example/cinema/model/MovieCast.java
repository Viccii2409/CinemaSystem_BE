package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MovieCast")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieCastID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "CastID", nullable = false)
    private Cast cast;
}
