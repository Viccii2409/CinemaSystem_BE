package com.springboot.cinema.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RatingID")
    private Long id;

    @Column(name = "Star", nullable = false)
    private int star;

    @Column(name = "Desciption")
    private String description;

    @OneToMany(mappedBy = "rating")
    private List<Feedback> feedbacks;
    // Các getter và setter
}
