package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.*;

@Data
@Embeddable
public class Ward {
	@Column(name = "ward")
	private String name;

}