package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@DiscriminatorValue("PAYCASH")
@NoArgsConstructor
@AllArgsConstructor
public class PayCash extends Payment {

	private float received;
	private float moneyReturned;

	@PrePersist
	private void generateBarcode() {
		if (this.getBarcode() == null || this.getBarcode().isEmpty()) {
			this.setBarcode("PAYCASH" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase());
		}
	}

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