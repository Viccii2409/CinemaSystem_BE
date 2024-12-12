package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomShowtimeDto {
    private long roomId;
    private String roomName;
    private List<ShowtimeDto> showtimes;

    // Constructor cho câu truy vấn JPQL
    public RoomShowtimeDto(long roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.showtimes = new ArrayList<>(); // Khởi tạo danh sách showtimes rỗng
    }

    // Getter và Setter cho showtimes
    public List<ShowtimeDto> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(List<ShowtimeDto> showtimes) {
        this.showtimes = showtimes;
    }

    // Getter và Setter cho roomId, roomName
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}