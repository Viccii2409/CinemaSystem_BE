package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private String description;
	private String phone;
	private String email;
	private String image;

	@Formula("(SELECT COUNT(r.roomid) from Room r where r.theaterid = theaterID)")
	private int quantityRoom;
	private boolean status;

	@OneToMany(mappedBy = "theater")
	private List<Agent> agent;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Room> room;

	@Embedded
	private Address address;

}