package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "SeatReservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatReservationID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SeatID", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "ReservationTime", nullable = false)
    private Date reservationTime;

    @Column(name = "ExpiryTime", nullable = false)
    private Date expiryTime;


    // Các getter và setter
}
