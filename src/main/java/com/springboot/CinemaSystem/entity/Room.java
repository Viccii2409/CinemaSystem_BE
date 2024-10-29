package com.springboot.CinemaSystem.entity;

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
	private Theater theater;

	@ManyToOne
	@JoinColumn(name = "typeRoomID")
	private TypeRoom typeRoom;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Seat> seat;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Showtime> showtime;

}