package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("PAYQR")
public class PayQR extends Payment {

	private String image;

}