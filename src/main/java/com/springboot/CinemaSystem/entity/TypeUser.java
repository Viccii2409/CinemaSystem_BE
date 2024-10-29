package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeUserID")
	private long ID;
	private String name;
	private int ageStart;
	private int ageEnd;
	private float surcharge;

	@OneToMany(mappedBy = "typeUser", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<SeatTicket> seatTicket;

}