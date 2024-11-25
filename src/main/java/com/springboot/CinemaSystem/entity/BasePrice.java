package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = BasePrice.class)
public class BasePrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "basePriceID")
	private long ID;
	private float defaultPrice;
	private String createdAt;
	private String updatedAt;

	@OneToMany(mappedBy = "basePrice", cascade = CascadeType.ALL)
	private List<SeatTicket> seatTicket;

}