package com.springboot.CinemaSystem.entity;

import com.springboot.CinemaSystem.dto.TypeDiscountDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeDiscountID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "typeDiscount")
	private List<Discount> discount;


	public TypeDiscountDto toTypeDiscountDto() {
		return new TypeDiscountDto(this.ID, this.name);
	}
}