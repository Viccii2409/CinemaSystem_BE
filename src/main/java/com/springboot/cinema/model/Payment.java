package com.springboot.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long id;

    @Column(name = "PaymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "Amount", nullable = false)
    private float amount;

    @Column(name = "BarCode")
    private String barCode;

    @Column(name = "Received")
    private float received;

    @Column(name = "MoneyReturned")
    private float moneyReturned;

    @Column(name = "Bank")
    private String bank;

    @Column(name = "Name")
    private String name;

    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "Image")
    private String image;

    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "DiscountID")
    private Discount discount;

    @OneToMany(mappedBy = "payment")
    private List<TicketBought> ticketsBought;

    @ManyToOne
    @JoinColumn(name = "TypePayID", nullable = false)
    private TypePay typePay;

    // Các getter và setter
}
