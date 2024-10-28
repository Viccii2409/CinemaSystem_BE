package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Trailer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trailerID")
	private long ID;
	private String link;
	private String description;

	@OneToOne
	@JoinColumn(name = "movieID")
	private Movie movie;

}