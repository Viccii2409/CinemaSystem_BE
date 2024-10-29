package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.*;

@Data
@Embeddable
public class District {
	@Column(name = "district")
	private String name;

}