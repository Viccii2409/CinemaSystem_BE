package com.springboot.CinemaSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueStat {

	private Date date;
	private int month;
	private int quarter;
	private int year;
	private float revenue;
	List<Payment> payment;

}