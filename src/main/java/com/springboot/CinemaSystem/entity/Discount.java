package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
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
	@JsonIgnore
	private List<Customer> customer;

	@ManyToOne
	@JoinColumn(name = "typeDiscountID")
	private TypeDiscount typeDiscount;

	@OneToMany(mappedBy = "discount")
	@JsonIgnore
	private List<Payment> payment;

}