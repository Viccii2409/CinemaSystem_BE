package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TimeFrame")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TimeFrameID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "TimeStart", nullable = false)
    private Time timeStart;

    @Column(name = "TimeEnd", nullable = false)
    private Time timeEnd;

    @Column(name = "Surcharge", nullable = false)
    private float surcharge;

    @OneToMany(mappedBy = "timeFrame")
    private List<SeatTicket> seatTickets;
    // Các getter và setter
}
