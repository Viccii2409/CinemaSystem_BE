package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Name {

	private String firstName;
	private String midName;
	private String lastName;

	@Transient
	private String fullname;

}