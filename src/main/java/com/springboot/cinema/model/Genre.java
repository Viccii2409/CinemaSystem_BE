package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Genre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenreID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status", nullable = false)
    private boolean status; // Đảm bảo thuộc tính status là kiểu boolean

    @OneToMany(mappedBy = "genre")
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "genre")
    private List<UserGenre> userGenres;


}
