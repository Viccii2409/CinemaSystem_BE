package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class Cast {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "castID")
	private long ID;
	private String name;

	@ManyToMany(mappedBy = "cast")
	@JsonIgnore
	private List<Movie> movie;

}