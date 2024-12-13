package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.TypeSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSeatDto {
    private long id;
    private String name;
    private float surcharge;

    public static TypeSeatDto toTypeSeatDto(TypeSeat typeSeat) {
        TypeSeatDto dto = new TypeSeatDto();
        dto.setId(typeSeat.getID());
        dto.setName(typeSeat.getName());
        dto.setSurcharge(typeSeat.getSurcharge());
        return dto;
    }
}
