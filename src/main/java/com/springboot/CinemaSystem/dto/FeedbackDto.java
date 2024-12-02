package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Rating;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private long ID;
    private String text;
    private String date;
    private Rating rating;
    private UserDto user;  // Thay vì chỉ trả về username, bạn trả về đối tượng UserDto


}
