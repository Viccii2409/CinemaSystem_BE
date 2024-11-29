package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.TypeSeatDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeSeat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeSeatID")
	private long ID;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private float surcharge;

	@OneToMany(mappedBy = "typeSeat")
	@JsonIgnore
	private List<Seat> seats;

	@OneToMany(mappedBy = "typeSeat")
	@JsonIgnore
	private List<PayTypeCustomer> payTypeCustomers;

	public TypeSeatDto toTypeSeatDto() {
		return new TypeSeatDto(this.getID(), this.getName(), this.surcharge);
	}

}