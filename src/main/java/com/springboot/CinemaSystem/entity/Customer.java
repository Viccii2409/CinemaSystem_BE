package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.dto.DiscountDto;
import com.springboot.CinemaSystem.dto.UserRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

	private int points;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "levelID")
	private Level level;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre;



	public static Customer toCustomer(UserRegisterDto userRegisterDto) {
		Customer customer = new Customer();
		customer.setName(userRegisterDto.getName());
		customer.setGender(userRegisterDto.getGender());
		customer.setDob(userRegisterDto.getDob());
		customer.setAddress(userRegisterDto.getAddress());
		customer.setEmail(userRegisterDto.getEmail());
		customer.setAccount(new Account(userRegisterDto.getEmail(), userRegisterDto.getPassword()));
		return customer;
	}

//	public CustomerDto toCustomerDto() {
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
//						.collect(Collectors.toList())
//		);
//	}

	public CustomerDto toCustomerDto() {
		List<Booking> bookings = this.getBooking();
		List< BookingDto> bookingDtos = new ArrayList<>();
		if(bookings.size() > 0) {
			for(Booking b : bookings) {
				System.out.println(b.getID());
				bookingDtos.add(b.toBookingDto2());
			}
		}
		return new CustomerDto(
				this.getID(),
				this.getGender(),
				this.getDob(),
				this.getAddress(),
				this.getEmail(),
				this.getPhone(),
				this.getImage(),
				this.isStatus(),
				this.getStartDate(),
				this.getPoints(),
				this.getLevel().toLevelDto(),
				this.getDiscount().stream()
						.map(entry -> new DiscountDto(
								entry.getID()
						))
						.collect(Collectors.toList()),
				this.getName(),
				this.getAccount().getUsername(),
				bookingDtos
		);
	}
}