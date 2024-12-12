package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.dto.DiscountDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

	private int points;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "levelID")
	@JsonIgnoreProperties("customer")
	private Level level;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Booking> booking;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_discount",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "discountID")
	)
	private List<Discount> discount;

	public CustomerDto toCustomerDto() {
		return new CustomerDto(
				this.getID(),
				this.getGender(),
				this.getDob(),
				this.getAddress(),
				this.getEmail(),
				this.getPhone(),
				this.getImage(),
				this.getStartDate(),
				this.getName(),
				this.getPoints(),
				this.getLevel().toLevelDto(),
				this.getDiscount().stream()
						.map(entry -> new DiscountDto(
								entry.getID()
						))
						.collect(Collectors.toList())
		);
	}

//	public CustomerDto toCustomerDto2() {
//		List<Booking> bookings = this.getBooking();
//		List< BookingDto> bookingDtos = new ArrayList<>();
//		if(bookings.size() > 0) {
//			for(Booking b : bookings) {
//				bookingDtos.add(b.toBookingDto2());
//			}
//		}
//		return new CustomerDto(
//				this.getID(),
//				this.getGender(),
//				this.getDob(),
//				this.getAddress(),
//				this.getEmail(),
//				this.getPhone(),
//				this.getImage(),
//				this.getStartDate(),
//				this.getName(),
//				this.getPoints(),
//				this.getLevel().toLevelDto(),
//				this.getDiscount().stream()
//						.map(entry -> new DiscountDto(
//								entry.getID()
//						))
//						.collect(Collectors.toList()),
//				this.getName(),
//				bookingDtos
//		);
//	}
}