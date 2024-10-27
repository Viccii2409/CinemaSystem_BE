package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Showtime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShowtimeID")
    private Long id;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "StartTime", nullable = false)
    private Time startTime;

    @Column(name = "EndTime", nullable = false)
    private Time endTime;

    @Column(name = "Status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "showtime")
    private List<TicketBought> ticketsBought;

    @ManyToOne
    @JoinColumn(name = "MovieID", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "RoomID", nullable = false)
    private Room room;

    @OneToMany(mappedBy = "showtime")
    private List<SeatAvailability> seatAvailabilities;


    // Các getter và setter
}
