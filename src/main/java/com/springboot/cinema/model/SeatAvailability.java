package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SeatAvailability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatAvailabilityID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SeatID", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "ShowtimeID", nullable = false)
    private Showtime showtime;

    @Column(name = "IsAvailable", nullable = false)
    private boolean isAvailable;

    // Các getter và setter
}
