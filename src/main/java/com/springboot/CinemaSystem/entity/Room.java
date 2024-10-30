package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomID")
	private long ID;
	private String name;

	@Transient
	private int quantitySeat;
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "theaterID")
	@JsonBackReference
	private Theater theater;

	@ManyToOne
	@JoinColumn(name = "typeRoomID")
	@JsonManagedReference
	private TypeRoom typeRoom;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Seat> seat;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Showtime> showtime;

}