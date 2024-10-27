package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Cast")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CastID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    // Quan hệ với bảng Movie_Cast
    @OneToMany(mappedBy = "cast")
    private List<MovieCast> movieCasts;

    // Các getter và setter
}
