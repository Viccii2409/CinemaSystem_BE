package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class City {
	@Column(name = "city")
	private String name;

}