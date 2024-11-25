package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typePay", discriminatorType = DiscriminatorType.STRING)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentID")
	private long ID;
	private LocalDateTime date;
	private float totalPrice;
	private float discountPrice;
	private float amount;
	private String barcode;

	@Transient
	private int quantityTicket;

	@Transient
	private String agentName;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookingID")
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "agentID")
	private Agent agent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discountID")
	private Discount discount;

	@OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
	private List<PayTypeCustomer> payTypeCustomers;

	@PrePersist
	private void generateBarcode() {
		if (barcode == null || barcode.isEmpty()) {
			this.barcode = "PAY" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
		}
	}

}