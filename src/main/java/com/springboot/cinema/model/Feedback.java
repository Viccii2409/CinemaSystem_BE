package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID")
    private Long id;

    @Column(name = "Text", nullable = false)
    private String text;

    @Column(name = "Date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "RatingID", nullable = false)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "FeedbackID")
    private TicketBought ticketBought;


    // Các getter và setter
}
