package com.springboot.CinemaSystem.entity;

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
}
