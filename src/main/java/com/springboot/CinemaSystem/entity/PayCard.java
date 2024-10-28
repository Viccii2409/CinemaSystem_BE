package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("PAYCARD")
public class PayCard extends Payment {

	private String bank;
	private String name;
	private String accountNumber;

}