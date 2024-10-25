package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Type_User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeUserID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "AgeStart", nullable = false)
    private Integer ageStart;

    @Column(name = "AgeEnd", nullable = false)
    private Integer ageEnd;

    @Column(name = "Surcharge", nullable = false)
    private float surcharge;

    @OneToMany(mappedBy = "typeUser")
    private Set<SeatTicket> seatTickets;

    // Các getter và setter
}
