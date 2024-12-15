package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private BookingDto booking;

    public FeedbackDto(long ID, String text, LocalDateTime date, int star, BookingDto booking) {
        this.ID=ID;
        this.text=text;
        this.date=date;
        this.star=star;
        this.booking=booking;
    }

    public static FeedbackDto toFeedbackDto(Feedback feedback) {
        FeedbackDto dto = new FeedbackDto();
        dto.setID(feedback.getID());
        dto.setText(feedback.getText());
        dto.setDate(feedback.getDate());
        dto.setStar(feedback.getStar());
        if(feedback.getBooking() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(feedback.getBooking().getUser().getID());
            userDto.setName(feedback.getBooking().getUser().getName()); // Đảm bảo UserDto có phương thức setUsername
            dto.setUser(userDto);
        }
        return dto;
    }
}
