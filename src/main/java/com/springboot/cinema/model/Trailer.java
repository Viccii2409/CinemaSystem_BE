package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Trailer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrailerID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @Column(name = "Link", nullable = false)
    private String link;

    @Column(name = "Description")
    private String description;

    // Các getter và setter
}
