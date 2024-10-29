package com.springboot.CinemaSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class TypeRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "typeRoomID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "typeRoom", cascade = CascadeType.ALL)
	List<Room> room;

}