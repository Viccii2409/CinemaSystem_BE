package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.SelectedSeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedSeatDto {
    private long id;
    private long userid;
    private long seatid;
    private LocalDateTime start;
    private LocalDateTime end;
    private String status;

    public static SelectedSeatDto toSelectedSeatDto(SelectedSeat selectedSeat) {
        SelectedSeatDto dto = new SelectedSeatDto();
        dto.setId(selectedSeat.getID());
        dto.setUserid(selectedSeat.getUser().getID());
        dto.setSeatid(selectedSeat.getSeat().getID());
        dto.setStart(selectedSeat.getStart());
        dto.setEnd(selectedSeat.getEnd());
        dto.setStatus(selectedSeat.getStatus());
        return dto;
    }
}
