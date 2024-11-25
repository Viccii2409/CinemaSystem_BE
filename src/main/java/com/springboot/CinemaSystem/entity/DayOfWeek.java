package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@OneToMany(mappedBy = "dayOfWeek", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Showtime> showtimes;

}