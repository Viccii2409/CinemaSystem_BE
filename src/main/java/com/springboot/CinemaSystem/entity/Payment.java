package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typePay", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
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
	private String status;  // pending, confirmed, expired
//
//	@Transient
//	private int quantityTicket;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookingID")
	private Booking booking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discountID")
	private Discount discount;

	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PayTypeCustomer> payTypeCustomers;

	public Payment(long ID) {
		this.ID = ID;
	}

	@PrePersist
	private void prePersistDate() {
		if (this.date == null) {
			this.date = LocalDateTime.now();
		}
	}

}