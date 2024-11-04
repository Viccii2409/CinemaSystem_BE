package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Slideshow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slideshowID")
    private long ID;
    private String url;

    @OneToOne
    @JoinColumn(name="movieID")
    @JsonIgnoreProperties("slideshow")
    private Movie movie;
}
