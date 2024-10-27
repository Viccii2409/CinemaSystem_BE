package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LanguageID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    // Quan hệ với Movie
    @OneToMany(mappedBy = "language")
    private List<Movie> movies;

    // Các getter và setter
}
