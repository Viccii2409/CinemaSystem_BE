package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDto {
    private long ID;
    private String name;
    private String phone;
    private String email;
    private String image;
    private String address;
    private String ward;
    private String district;
    private String city;
    private String description;
    private int quantityRoom;
    private boolean status;

    private List<MovieShowtimeDto> movie;
    private List<RoomDto> room;

    public TheaterDto(long ID, String name, List<MovieShowtimeDto> movie) {
        this.ID = ID;
        this.name = name;
        this.movie = movie;
    }

    public static TheaterDto toNameTheaterDto (Theater theater) {
        TheaterDto dto = new TheaterDto();
        dto.setID(theater.getID());
        dto.setName(theater.getName());
        return dto;
    }

    public static TheaterDto toTheaterDto (Theater theater) {
        TheaterDto dto = toNameTheaterDto(theater);
        dto.setAddress(theater.getFullAddress());
        dto.setQuantityRoom(theater.getQuantityRoom());
        dto.setStatus(theater.isStatus());
        return dto;
    }

    public static TheaterDto toTheaterView (Theater theater) {
        TheaterDto dto = toTheaterDto(theater);
        dto.setDescription(theater.getDescription());
        dto.setPhone(theater.getPhone());
        dto.setEmail(theater.getEmail());
        dto.setImage(theater.getImage());
        return dto;
    }

    public static TheaterDto toTheaterRoomDto(Theater theater) {
        TheaterDto dto = toNameTheaterDto(theater);
        List<RoomDto> roomDtos = theater.getRoom().stream()
                .map(entry -> RoomDto.toRoomDto(entry))
                .collect(Collectors.toList());
        dto.setRoom(roomDtos);
        dto.setStatus(theater.isStatus());
        return dto;
    }
}

