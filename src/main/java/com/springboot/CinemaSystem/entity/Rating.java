package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ratingID")
	private long ID;
	private int star;
	private String description;

	@OneToMany(mappedBy = "rating", cascade = CascadeType.ALL)
	private List<Feedback> feedback;

}