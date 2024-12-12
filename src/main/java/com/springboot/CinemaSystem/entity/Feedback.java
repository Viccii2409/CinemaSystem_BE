package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.MovieFeedbackDto;
import com.springboot.CinemaSystem.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
	private LocalDateTime date;

//	@ManyToOne
//	@JoinColumn(name = "ratingID")
//	private Rating rating;
	private int star;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonIgnoreProperties("feedback")
	private Movie movie;

	@OneToOne
	@JoinColumn(name = "bookingID")
	private Booking booking;

	public MovieFeedbackDto toMovieFeedbackDto() {
		MovieFeedbackDto feedbackDto = new MovieFeedbackDto();
		feedbackDto.setID(this.ID);
		feedbackDto.setText(this.text);
		feedbackDto.setDate(this.date);
		feedbackDto.setStar(this.star);
		// Lấy thông tin User thông qua Booking
		if (this.booking != null && this.booking.getCustomer() != null) {
			UserDto userDto = new UserDto();
			userDto.setId(this.booking.getCustomer().getID());
			userDto.setName(this.booking.getCustomer().getName()); // Đảm bảo UserDto có phương thức setUsername
			feedbackDto.setUser(userDto);
		}
		return feedbackDto;
	}
//	public FeedbackDto toFeedbackDto() {
//		BookingDto bookingDto = new BookingDto(booking.getID(),
//				booking.getShowtime().getMovie().getId(),
//				booking.getUser().getName(),
//				booking.getUser().getImage());
//		return new FeedbackDto(this.ID, this.text, this.date, this.star,bookingDto);	}
}


