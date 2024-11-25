package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class BasePrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	private float defaultPrice;
	private String createdAt;
	private String updatedAt;

	@OneToMany(mappedBy = "basePrice", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Showtime> showtimes;

}