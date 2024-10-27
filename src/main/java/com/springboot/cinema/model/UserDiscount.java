package com.springboot.cinema.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserDiscount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserDiscountID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UserID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "DiscountID", nullable = false)
    private Discount discount;
}
