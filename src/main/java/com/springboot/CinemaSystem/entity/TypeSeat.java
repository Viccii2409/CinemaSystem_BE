package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@JsonBackReference
	private List<Seat> seats;

}