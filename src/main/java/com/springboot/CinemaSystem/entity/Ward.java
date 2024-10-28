package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.*;

@Embeddable
public class Ward {
	@Column(name = "ward")
	private String name;

}