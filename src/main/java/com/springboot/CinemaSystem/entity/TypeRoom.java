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

	private float surcharge;

	@OneToMany(mappedBy = "typeRoom" )
	@JsonIgnore
	List<Room> room;

	public TypeRoomDto toTypeRoomDto() {
		TypeRoomDto typeRoomDto = new TypeRoomDto();
		typeRoomDto.setId(this.getID());
		typeRoomDto.setName(this.getName());
		typeRoomDto.setSurcharge(this.surcharge);
		return typeRoomDto;
	}
	@Override
	public String toString() {
		return "TypeRoom{id=" + ID + ", name='" + name + "'}";
	}

}