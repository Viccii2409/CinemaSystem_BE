package com.springboot.CinemaSystem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private long ID;
    private String text;
    private LocalDateTime date;
    private int star;
    private UserDto user;  // Thay vì chỉ trả về username, bạn trả về đối tượng UserDto


}
