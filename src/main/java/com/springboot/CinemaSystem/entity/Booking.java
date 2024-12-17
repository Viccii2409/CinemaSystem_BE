package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingID")
	private long ID;

	private LocalDateTime date;
	private String barcode;
	private String typeBooking;	//	ONLINE, OFFLINE

	private String nameCustomer;
	private String emailCustomer;
	private String phoneCustomer;
	private boolean status;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showtimeID")
	private Showtime showtime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userID")
	private User user;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> ticket = new ArrayList<>();

	@OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
	private Payment payment;

	@OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("booking")
	private Feedback feedback;

	@PrePersist
	private void prePersistDateAndBarcode() {
		if (this.date == null) {
			this.date = LocalDateTime.now();
		}

		// Tạo mã barcode duy nhất nếu chưa có
		if (this.barcode == null || this.barcode.isEmpty()) {
			StringBuilder numericBarcode = new StringBuilder();
			for (int i = 0; i < 12; i++) {
				int randomDigit = (int) (Math.random() * 10);
				numericBarcode.append(randomDigit);
			}
			this.barcode = numericBarcode.toString();
		}
	}
}
