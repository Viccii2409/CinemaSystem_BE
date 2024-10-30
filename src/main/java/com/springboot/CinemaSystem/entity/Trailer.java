package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@OneToOne
	@JoinColumn(name = "movieID")
	@JsonBackReference
	private Movie movie;

}