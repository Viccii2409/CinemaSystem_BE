package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LevelID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    // Quan hệ với Movie
    @OneToMany(mappedBy = "level")
    private List<User> users;

    // Các getter và setter
}
