package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TypeSeat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeSeatID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surcharge", nullable = false)
    private float surcharge;

    @OneToMany(mappedBy = "typeSeat")
    private List<Seat> seats;
    // Các getter và setter
}
