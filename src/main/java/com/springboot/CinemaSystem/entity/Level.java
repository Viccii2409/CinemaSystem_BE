package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "levelID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
	private List<Customer> customer;

}