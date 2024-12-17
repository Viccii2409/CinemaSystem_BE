package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TypeCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeCustomerID")
	private long ID;
	@Column(nullable = false)
	private String name;
	@Column(nullable = true)
	private Integer ageStart;
	@Column(nullable = true)
	private Integer ageEnd;
	private float discount;

	@OneToMany(mappedBy = "typeCustomer", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<PayTypeCustomer> payTypeCustomers;

	public TypeCustomer(long ID) {
		this.ID = ID;
	}
}