package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Seat;
import com.springboot.CinemaSystem.entity.TypeSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private long id;
    private String name;
    private int seatNum;
    private int rowNum;
    private boolean status;
    private TypeSeatDto typeSeat;

    public static SeatDto toSeatDto (Seat seat) {
        SeatDto dto = new SeatDto();
        dto.setId(seat.getID());
        dto.setName(seat.getName());
        dto.setSeatNum(seat.getSeatNum());
        dto.setRowNum(seat.getRowNum());
        dto.setStatus(seat.isStatus());
        dto.setTypeSeat(TypeSeatDto.toTypeSeatDto(seat.getTypeSeat()));
        return dto;
    }
}
