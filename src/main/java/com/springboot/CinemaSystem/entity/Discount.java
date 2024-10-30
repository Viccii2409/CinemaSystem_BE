package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private boolean status;

	@ManyToMany(mappedBy = "discount")
	@JsonBackReference
	private List<Customer> customer;

	@ManyToOne
	@JoinColumn(name = "typeDiscountID")
	@JsonManagedReference
	private TypeDiscount typeDiscount;

	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Payment> payment;

}