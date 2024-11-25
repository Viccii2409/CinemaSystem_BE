package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SelectedSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;  // pending, confirmed, expired

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seatid")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtimeid")
    private Showtime showtime;
}
