package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
	public boolean addFeedback(Feedback feedback);
	public List<Feedback> getFeedbackByMovie(int movieID);
	public Feedback getFeedbackByID(int feedbackID);
	public List<Feedback> getFeedbacksByCustomerID(int customerID);
	public List<Feedback> getFeedbacksByRating(int ratingID, int movieID);


}