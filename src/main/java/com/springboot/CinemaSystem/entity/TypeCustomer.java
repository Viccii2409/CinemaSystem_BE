package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
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

}