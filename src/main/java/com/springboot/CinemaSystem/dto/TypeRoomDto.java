package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.TypeRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeRoomDto {
    private long id;
    private String name;
    private float surcharge;

    public static TypeRoomDto toTypeRoomDto (TypeRoom typeRoom) {
        TypeRoomDto typeRoomDto = new TypeRoomDto();
        typeRoomDto.setId(typeRoom.getID());
        typeRoomDto.setName(typeRoom.getName());
        typeRoomDto.setSurcharge(typeRoom.getSurcharge());
        return typeRoomDto;

    }
}
