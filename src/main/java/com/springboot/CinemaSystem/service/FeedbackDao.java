package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
	public Feedback addFeedback(Feedback feedback);
	public List<FeedbackDto> getFeedbackByMovie(long movieID);
	public Feedback getFeedbackByID(int feedbackID);
	public List<Feedback> getFeedbacksByCustomerID(int customerID);
	public List<Feedback> getFeedbacksByRating(int ratingID, int movieID);


	boolean existsByBookingIdAndMovieId(long bookingID, long movieID);
}