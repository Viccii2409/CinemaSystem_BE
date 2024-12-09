package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.DiscountAddDto;
import com.springboot.CinemaSystem.dto.DiscountDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
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
	private Date start;
	private Date end;

  	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "typeDiscountID")
	private TypeDiscount typeDiscount;

	@ManyToMany(mappedBy = "discount")
	@JsonIgnore
	private List<User> user;

	@OneToMany(mappedBy = "discount")
	private List<Payment> payment;

	public Discount(long ID) {
		this.ID = ID;
	}

	public static Discount toDiscount(DiscountAddDto discountAddDto) {
		Discount discount = new Discount();
		discount.setID(discountAddDto.getId());
		discount.setName(discountAddDto.getName());
		discount.setReducedValue(discountAddDto.getReducedValue());
		discount.setDiscountCode(discountAddDto.getDiscountCode());
		discount.setStart(Date.valueOf(discountAddDto.getStart()));
		discount.setEnd(Date.valueOf(discountAddDto.getEnd()));
		discount.setDescription(discountAddDto.getDescription());
		discount.setStatus(discountAddDto.isStatus());
		return discount;
	}

	public DiscountDto toDiscountDto() {
		return new DiscountDto(this.ID, this.name, this.typeDiscount.toTypeDiscountDto(), this.reducedValue, this.discountCode,
				this.start, this.end, this.description, this.image, this.status);
	}
}