package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discountID")
	private long ID;
	private String name;
	private float reducedValue;
	private String discountCode;
	private String image;
	private String description;
	private boolean status;

	@ManyToMany(mappedBy = "discount")
	private List<Customer> customer;

	@ManyToOne
	@JoinColumn(name = "typeDiscountID")
	private TypeDiscount typeDiscount;

	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	private List<Payment> payment;

}