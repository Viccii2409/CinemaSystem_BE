package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.Feedback;
import com.springboot.CinemaSystem.service.FeedbackDao;

import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {

	@Override
	public boolean addFeedback(Feedback feedback) {
		return false;
	}

	@Override
	public List<Feedback> getFeedbackByMovie(int movieID) {
		return List.of();
	}

	@Override
	public Feedback getFeedbackByID(int feedbackID) {
		return null;
	}

	@Override
	public List<Feedback> getFeedbacksByCustomerID(int customerID) {
		return List.of();
	}

	@Override
	public List<Feedback> getFeedbacksByRating(int ratingID, int movieID) {
		return List.of();
	}
}