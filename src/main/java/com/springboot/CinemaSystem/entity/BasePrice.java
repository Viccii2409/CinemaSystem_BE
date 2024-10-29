package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
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