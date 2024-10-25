package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "DayOfWeek")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DayOfWeekID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "DayStart", nullable = false)
    private Integer dayStart;

    @Column(name = "DayEnd", nullable = false)
    private String dayEnd;

    @Column(name = "Surcharge", nullable = false)
    private float surcharge;

    @OneToMany(mappedBy = "dayOfWeek")
    private Set<SeatTicket> seatTickets;
    // Các getter và setter
}
