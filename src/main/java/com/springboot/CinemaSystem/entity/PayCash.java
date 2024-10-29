package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("PAYCASH")
public class PayCash extends Payment {

	private float received;
	private float moneyReturned;

}