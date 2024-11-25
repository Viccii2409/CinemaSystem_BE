package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DiscriminatorValue("PAYCASH")
@NoArgsConstructor
@AllArgsConstructor
public class PayCash extends Payment {

	private float received;
	private float moneyReturned;

	@Override
	public String toString() {
		return "PayCash{" +
				"received=" + received +
				", moneyReturned=" + moneyReturned +
				", amount=" + super.getAmount() +
				", booking=" + super.getBooking() +
				", agent=" + super.getAgent() +
				", date=" + super.getDate() +
				", barcode='" + super.getBarcode() + '\'' +
				'}';
	}
}