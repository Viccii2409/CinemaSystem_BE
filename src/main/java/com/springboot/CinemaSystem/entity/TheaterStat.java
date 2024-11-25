package com.springboot.CinemaSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterStat extends Theater {

	private Date dateStart;
	private Date dateEnd;
	private float revenue;

}