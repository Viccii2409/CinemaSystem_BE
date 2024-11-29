package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.BookingDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

	public BookingDto toBookingDto() {
		List<String> nameSeats = new ArrayList<>();
		for(Ticket t : this.getTicket()) {
			nameSeats.add(t.getSeat().getName());
		}
		Customer customer = this.getCustomer();
		Name name = (customer != null) ? customer.getName() : null;
		return new BookingDto(
				this.ID,
				this.getDate(),
				this.getBarcode(),
				nameSeats,
				this.getShowtime().getDate(),
				this.getShowtime().getStartTime(),
				this.getShowtime().getEndTime(),
				this.getShowtime().getMovie().getTitle(),
				this.getShowtime().getMovie().getFirstImage(),
				this.getShowtime().getRoom().getTheater().getName(),
				this.getShowtime().getRoom().getTheater().getFullAddress(),
				this.getShowtime().getRoom().getName(),
				this.getShowtime().getRoom().getTypeRoom().getName(),
				this.getPayment().getTotalPrice(),
				this.getPayment().getDiscountPrice(),
				this.getPayment().getAmount(),
				(name != null) ? name.getFullname() : "",
				(customer != null) ? customer.getPhone() : "",
				(customer != null) ? customer.getEmail() : ""
		);
	}

}