package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingID")
	private long ID;
	private LocalDateTime date;
	private String barcode;

	@Transient
	private float amount;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> ticket;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showtimeID")
	private Showtime showtime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	private Customer customer;

	@OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
	private Payment payment;

	@OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
	private Feedback feedback;

	@PrePersist
	private void generateBarcode() {
		StringBuilder numericBarcode = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			int randomDigit = (int) (Math.random() * 10);
			numericBarcode.append(randomDigit);
		}
		this.barcode = numericBarcode.toString();
	}

}