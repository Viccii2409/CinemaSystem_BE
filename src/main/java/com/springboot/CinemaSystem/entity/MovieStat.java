package com.springboot.CinemaSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieStat extends Movie {

	private Date dateStart;
	private Date dateEnd;
	private float renvenue;

}