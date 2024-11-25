package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springboot.CinemaSystem.dto.LevelDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "levelID")
	private long ID;
	private String name;
	private int quantityTicket;

	@OneToMany(mappedBy = "level")
	@JsonIgnoreProperties("level")
	private List<Customer> customer;

	public LevelDto toLevelDto() {
		return new LevelDto(this.ID, this.name, this.quantityTicket);
	}

}