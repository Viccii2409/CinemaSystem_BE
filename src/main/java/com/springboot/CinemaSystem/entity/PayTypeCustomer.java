package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PayTypeCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentID")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeCustomerID")
    private TypeCustomer typeCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeSeatID")
    private TypeSeat typeSeat;

}
