package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "Discount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DiscountID")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "ReducedValue", nullable = false)
    private float reducedValue;

    @Column(name = "DiscountCode", nullable = false)
    private String discountCode;

    @Column(name = "Image")
    private String image;

    @Column(name = "Description")
    private String discription;

    @Column(name = "Status", nullable = false)
    private boolean status;

    // Quan hệ với bảng User_Discount
    @ManyToMany(mappedBy = "discounts")
    @Column(name = "UserID", nullable = false)
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "TypeDiscountID", nullable = false)
    private TypeDiscount typeDiscount;
    // Các getter và setter
}
