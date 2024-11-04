package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.TypeRoomDto;
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
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "typeRoom" )
	@JsonIgnoreProperties("typeRoom")
	List<Room> room;

	public TypeRoomDto toTypeRoomDto() {
		TypeRoomDto typeRoomDto = new TypeRoomDto();
		typeRoomDto.setId(this.getID());
		typeRoomDto.setName(this.getName());
		return typeRoomDto;
	}

}