package com.springboot.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

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
    @JsonIgnore
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<UserGenre> userGenres;


}
