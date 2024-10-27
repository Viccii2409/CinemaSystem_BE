package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BasePrice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BasePriceID")
    private Long id;

    @Column(name = "DefaultPrice", nullable = false)
    private float defaultPrice;

    @Column(name = "CreatedDate")
    private Date createDate;

    @Column(name = "UpdatedAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "basePrice")
    private List<SeatTicket> seatTickets;

    // Các getter và setter
}
