package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TypeUser.class)
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
	private List<SeatTicket> seatTicket;

}