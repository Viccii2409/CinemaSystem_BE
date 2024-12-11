package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto {
    private long ID;
    private String name;
    private int quantityTicket;

    public static LevelDto toLevelDto(Level level) {
        return new LevelDto(level.getID(), level.getName(), level.getQuantityTicket());
    }
}
