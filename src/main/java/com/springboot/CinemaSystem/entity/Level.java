package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.LevelDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "levelID")
	private long ID;
	private String name;
	private int quantityTicket;

	@OneToMany(mappedBy = "level")
	@JsonIgnore
	private List<Customer> customer;

	public Level(long ID) {
		this.ID = ID;
	}

	public LevelDto toLevelDto() {
		return new LevelDto(this.ID, this.name, this.quantityTicket);
	}

}