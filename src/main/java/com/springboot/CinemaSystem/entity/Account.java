package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Column(nullable = false, unique = true)
	private String username;
	private String password;
}