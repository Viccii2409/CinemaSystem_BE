package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeDiscountID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "typeDiscount", cascade = CascadeType.ALL)
	private List<Discount> discount;


}