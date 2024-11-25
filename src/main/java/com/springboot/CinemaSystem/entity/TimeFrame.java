package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TimeFrame.class)
public class TimeFrame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeFrameID")
	private long ID;
	private String name;
	private Time timeStart;
	private Time timeEnd;
	private float surcharge;

	@OneToMany(mappedBy = "timeFrame", cascade = CascadeType.ALL)
	private List<SeatTicket> seatTicket;

}