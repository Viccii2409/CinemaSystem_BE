package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.RoomDto;
import com.springboot.CinemaSystem.dto.RoomSeatDto;
import com.springboot.CinemaSystem.dto.SeatDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"showtime", "seat", "theater"})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomID")
	private long ID;
	private String name;

	@Formula("(SELECT COUNT(s.seatid) FROM Seat s WHERE s.roomid = roomid AND s.status = 1)")
	private int quantitySeat;

	@Column(nullable = false)
	private int numRows;
	@Column(nullable = false)
	private int numColumn;
	@Column(nullable = false)
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "typeRoomID", nullable = false)
	private TypeRoom typeRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theaterID", nullable = false)
	@JsonBackReference(value = "theater-room")
	private Theater theater;


	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference(value = "room-seat")
	private List<Seat> seat;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("room")
	private List<Showtime> showtime;

	public RoomDto toRoomDto() {
		RoomDto roomDto = new RoomDto();
		roomDto.setId(this.getID());
		roomDto.setName(this.getName());
		roomDto.setTypeRoom(this.getTypeRoom().toTypeRoomDto());
		roomDto.setQuantitySeat(this.getQuantitySeat());
		roomDto.setStatus(this.isStatus());
		return roomDto;
	}

	public RoomSeatDto toRoomSeatDto() {
		List<SeatDto> seatDtos = new ArrayList<>();
		for (Seat s : this.getSeat()) {
			seatDtos.add(s.toSeatDto());
		}
		return new RoomSeatDto(this.ID, this.name, this.getTypeRoom().toTypeRoomDto(), this.getQuantitySeat(), this.numRows, this.numColumn, this.status, seatDtos, this.getTheater().getName());
	}
	@Override
	public String toString() {
		return "Room{id=" + ID + ", name='" + name + "', typeRoom=" + (typeRoom != null ? typeRoom.getName() : "N/A") + ", status=" + status + "}";
	}


}