package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageID")
    private Long id;

    @Column(name = "Link", nullable = false)
    private String link;

    @Column(name = "Type", nullable = false)
    private Boolean type;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;
    // Các getter và setter
}
