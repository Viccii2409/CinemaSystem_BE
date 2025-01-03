package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theaterID")
	private long ID;
	@Column(nullable = false)
	private String name;


	@Lob
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private String phone;
	private String email;
	private String image;

	@Formula("(SELECT COUNT(r.roomid) from Room r where r.theaterid = theaterID)")
	private int quantityRoom;
	@Column(nullable = false)
	private boolean status;

	@OneToOne(mappedBy = "theater", fetch = FetchType.LAZY)
	private Manager manager;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "theater-room")
	private List<Room> room = new ArrayList<>();

	@Embedded
	private Address address;

	public String getFullAddress() {
		return this.getAddress().getAddressDetail() + ", " +
				this.getAddress().getWard() + ", " +
				this.getAddress().getDistrict() + ", " +
				this.getAddress().getCity();
	}

	public static Theater convertTheaterAddtoTheater(TheaterDto dto) {
		Theater theater = new Theater();
		theater.setName(dto.getName());
		theater.setPhone(dto.getPhone());
		theater.setEmail(dto.getEmail());
		theater.setDescription(dto.getDescription());
		Address address = new Address(dto.getAddress(), dto.getWard(), dto.getDistrict(), dto.getCity());
		theater.setAddress(address);
		theater.setStatus(true);
		return theater;
	}

	public static Theater convertTheaterEdittoTheater(TheaterDto dto) {
		Theater theater = convertTheaterAddtoTheater(dto);
		theater.setID(dto.getId());
		return theater;
	}

}