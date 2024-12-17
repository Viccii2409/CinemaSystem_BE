package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.entity.Feedback;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.repository.BookingRepository;
import com.springboot.CinemaSystem.repository.FeedbackRepository;
import com.springboot.CinemaSystem.service.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackDaoImpl implements FeedbackDao {
	private FeedbackRepository feedbackRepository;
	private BookingRepository bookingRepository;
	@Autowired
	public FeedbackDaoImpl(FeedbackRepository feedbackRepository, BookingRepository bookingRepository){
		this.bookingRepository=bookingRepository;
		this.feedbackRepository=feedbackRepository;
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