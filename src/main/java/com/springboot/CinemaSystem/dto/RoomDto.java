package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private long id;
    private String name;
    private TypeRoomDto typeRoom;
    private int quantitySeat;
    private boolean status;

    private int numRows;
    private int numColumn;
    private List<SeatDto> seat;
    private String theaterName;
    private long quantityShowtime;

    public static RoomDto toRoomDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getID());
        roomDto.setName(room.getName());
        roomDto.setTypeRoom(TypeRoomDto.toTypeRoomDto(room.getTypeRoom()));
        roomDto.setQuantitySeat(room.getQuantitySeat());
        roomDto.setStatus(room.isStatus());
        roomDto.setQuantityShowtime(room.getShowtime().size());
        return roomDto;
    }

    public static RoomDto toRoomSeatDto (Room room) {
        RoomDto dto = toRoomDto(room);
        dto.setNumRows(room.getNumRows());
        dto.setNumColumn(room.getNumColumn());
        dto.setStatus(room.isStatus());
        List<SeatDto> seatDtos = room.getSeat().stream()
                .map(entry -> SeatDto.toSeatDto(entry))
                .collect(Collectors.toList());
        dto.setSeat(seatDtos);
        dto.setTheaterName(room.getTheater().getName());
        return dto;
    }
}
