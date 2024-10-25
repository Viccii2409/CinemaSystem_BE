package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SeatTicket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatTicketID")
    private Long id;

    @Column(name = "Surcharge", nullable = false)
    private float surcharge;

    @Column(name = "FinalPrice", nullable = false)
    private float finalPrice;

    @OneToOne
    @JoinColumn(name = "TicketBoughtID", nullable = false)
    private TicketBought ticketBought;

    @ManyToOne
    @JoinColumn(name = "SeatID", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "TypeUserID", nullable = false)
    private TypeUser typeUser;

    @ManyToOne
    @JoinColumn(name = "DayOfWeekID", nullable = false)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "TimeFrameID", nullable = false)
    private TimeFrame timeFrame;

    @ManyToOne
    @JoinColumn(name = "BasePriceID", nullable = false)
    private BasePrice basePrice;

    // Các getter và setter
}
