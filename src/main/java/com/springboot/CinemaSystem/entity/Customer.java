package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.CinemaSystem.dto.*;
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_genre",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "genreID")
	)
	private List<Genre> genre = new ArrayList<>();



	public static Customer toCustomer(UserRegisterDto userRegisterDto) {
		Customer customer = new Customer();
		customer.setName(userRegisterDto.getName());
		customer.setGender(userRegisterDto.getGender());
		customer.setDob(userRegisterDto.getDob());
		customer.setPhone(userRegisterDto.getPhone());
		customer.setAddress(userRegisterDto.getAddress());
		customer.setEmail(userRegisterDto.getEmail());
		customer.setAccount(new Account(userRegisterDto.getEmail(), userRegisterDto.getPassword()));
		return customer;
	}

}