package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbackID")
	private long ID;

	@Lob
	@Column(name = "text", columnDefinition = "TEXT")
	private String text;
	private String date;

	@ManyToOne
	@JoinColumn(name = "ratingID")
	private Rating rating;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonIgnoreProperties("feedback")
	private Movie movie;

	@OneToOne
	@JoinColumn(name = "bookingID")
	private Booking booking;

	public FeedbackDto toFeedbackDto() {
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setID(this.ID);
		feedbackDto.setText(this.text);
		feedbackDto.setDate(this.date);
		feedbackDto.setRating(this.rating);
		// Lấy thông tin User thông qua Booking
		if (this.booking != null && this.booking.getCustomer() != null) {
			UserDto userDto = new UserDto();
			userDto.setId(this.booking.getCustomer().getID());
			userDto.setName(this.booking.getCustomer().getName()); // Đảm bảo UserDto có phương thức setUsername
			feedbackDto.setUser(userDto);
		}
		return feedbackDto;
	}
}


