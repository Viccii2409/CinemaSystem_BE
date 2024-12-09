package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.dto.DiscountDto;
import com.springboot.CinemaSystem.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
	private String position;
	private Date dayInWork;

	@Transient
	private double revenue;

	@PrePersist
	private void prePerisistDayInWork() {
		if(this.dayInWork == null) {
			long currentTimeMillis = System.currentTimeMillis();
			this.dayInWork = new Date(currentTimeMillis);
		}
	}

	public EmployeeDto toEmployeeDto() {
		List<Booking> bookings = this.getBooking();
		List<BookingDto> bookingDtos = new ArrayList<>();
		if(bookings.size() > 0) {
			for(Booking b : bookings) {
				System.out.println(b.getID());
				bookingDtos.add(b.toBookingDto2());
			}
		}
		return new EmployeeDto(
				this.getID(),
				this.getGender(),
				this.getDob(),
				this.getAddress(),
				this.getEmail(),
				this.getPhone(),
				this.getImage(),
				this.getStartDate(),
				this.getDiscount().stream()
						.map(entry -> new DiscountDto(
								entry.getID()
						))
						.collect(Collectors.toList()),
				this.getName(),
				this.getAccount().getUsername(),
				bookingDtos,
				this.position,
				this.dayInWork,
				this.revenue
		);
	}

	public static Employee convertCustomerToEmployee(Customer customer) {
		Employee employee = new Employee();
		employee.setID(customer.getID());
		employee.setName(customer.getName());
		employee.setDob(customer.getDob());
		employee.setAddress(customer.getAddress());
		employee.setEmail(customer.getEmail());
		employee.setPhone(customer.getPhone());
		employee.setImage(customer.getImage());
		employee.setGender(customer.getGender());
		employee.setStatus(customer.isStatus());
		employee.setStartDate(customer.getStartDate());
		employee.setAccount(customer.getAccount());
		employee.setDiscount(customer.getDiscount());
		return employee;
	}
}