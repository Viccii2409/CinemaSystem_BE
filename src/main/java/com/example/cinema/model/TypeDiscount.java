package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TypeDiscount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeDiscountID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "typeDiscount")
    private List<Discount> discounts;

    // Các getter và setter
}
