package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

import java.util.*;

@Data
@Entity
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theaterID")
	private long ID;
	private String name;


	@Lob
	@Column(name = "description", columnDefinition = "TEXT")

	private String description;
	private String phone;
	private String email;
	private String image;

	@Formula("(SELECT COUNT(r.roomid) from Room r where r.theaterid = theaterID)")
	private int quantityRoom;
	private boolean status;

	@OneToMany(mappedBy = "theater")
	@JsonBackReference
	private List<Agent> agent;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Room> room;

	@Embedded
	private Address address;

}