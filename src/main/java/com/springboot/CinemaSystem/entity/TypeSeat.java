package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeSeat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeSeatID")
	private long ID;
	private String name;
	private float surcharge;

	@OneToMany(mappedBy = "typeSeat", cascade = CascadeType.ALL)
	private List<Seat> seats;

}