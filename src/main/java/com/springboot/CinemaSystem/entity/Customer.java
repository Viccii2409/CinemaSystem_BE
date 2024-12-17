package com.springboot.CinemaSystem.entity;

import com.springboot.CinemaSystem.dto.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre = new ArrayList<>();



	public static Customer toCustomer(UserDto userDto) {
		Customer customer = new Customer();
		customer.setName(userDto.getName());
		customer.setGender(userDto.getGender());
		customer.setDob(userDto.getDob());
		customer.setPhone(userDto.getPhone());
		customer.setAddress(userDto.getAddress());
		customer.setEmail(userDto.getEmail());
		customer.setAccount(new Account(userDto.getEmail(), userDto.getPassword()));
		return customer;
	}

}