package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typePay", discriminatorType = DiscriminatorType.STRING)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentID")
	private long ID;
	private String date;

	@Transient
	private float amount;
	private String barcode;

	@Transient
	private int agentID;

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<TicketBought> ticketBought;

	@ManyToOne
	@JoinColumn(name = "agentID")
	@JsonManagedReference
	private Agent agent;

	@ManyToOne
	@JoinColumn(name = "discountID")
	@JsonManagedReference
	private Discount discount;

}