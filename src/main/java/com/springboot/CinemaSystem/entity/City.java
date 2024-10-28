package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.*;

@Embeddable
public class City {
	@Column(name = "city")
	private String name;

}