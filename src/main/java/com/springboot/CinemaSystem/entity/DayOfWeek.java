package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class DayOfWeek {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dayOfWeekID")
	private long ID;
	private String name;
	private int dayStart;
	private int dayEnd;
	private float surcharge;

	@OneToMany(mappedBy = "dayOfWeek", cascade = CascadeType.ALL)
	private List<SeatTicket> seatTicket;

}