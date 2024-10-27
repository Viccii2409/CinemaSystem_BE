package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "TicketBought")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketBought {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketBoughtID")
    private Long id;

    @Column(name = "Date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ShowtimeID", nullable = false)
    private Showtime showtime;

    @ManyToOne
    @JoinColumn(name = "PaymentID", nullable = false)
    private Payment payment;

    @OneToOne(mappedBy = "ticketBought")
    private SeatTicket seatTicket;



    // Các getter và setter
}
