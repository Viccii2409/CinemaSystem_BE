package com.springboot.CinemaSystem.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Address {

	private String addressDetail;

	@Embedded
	private Ward ward;

	@Embedded
	private District district;

	@Embedded
	private City city;

}