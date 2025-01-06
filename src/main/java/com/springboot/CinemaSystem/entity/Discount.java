package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.DiscountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discountID")
	private long ID;
	private String name;
	private float reducedValue;
	private String discountCode;
	private String image;
	private LocalDate start;
	private LocalDate end;

  	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "typeDiscountID")
	private TypeDiscount typeDiscount;

	@ManyToMany(mappedBy = "discount")
	@JsonIgnore
	private List<User> user = new ArrayList<>();

	@OneToMany(mappedBy = "discount")
	private List<Payment> payment = new ArrayList<>();

	public Discount(long ID) {
		this.ID = ID;
	}

	public static Discount toDiscount(DiscountDto dto) {
		Discount discount = new Discount();
		discount.setID(dto.getId());
		discount.setName(dto.getName());
		discount.setReducedValue(dto.getReducedValue());
		discount.setDiscountCode(dto.getDiscountCode());
		discount.setStart(dto.getStart());
		discount.setEnd(dto.getEnd());
		discount.setDescription(dto.getDescription());
		discount.setStatus(dto.isStatus());
		return discount;
	}
}