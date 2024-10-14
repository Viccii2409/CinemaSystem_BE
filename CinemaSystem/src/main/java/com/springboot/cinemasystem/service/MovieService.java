package com.springboot.cinemasystem.service;

import java.util.List;

import com.springboot.cinemasystem.model.entity.Cast;
import com.springboot.cinemasystem.model.entity.Director;
import com.springboot.cinemasystem.model.entity.Feedback;
import com.springboot.cinemasystem.model.entity.Movie;
import com.springboot.cinemasystem.model.entity.Schedule;

public interface MovieService {

	/**
	 * 
	 * @param movie
	 */
	boolean addMovie(Movie movie);

	/**
	 * 
	 * @param movie
	 */
	boolean editMovie(Movie movie);

	/**
	 * 
	 * @param movieID
	 */
	Movie getMovie(int movieID);

	/**
	 * 
	 * @param genreID
	 */
	List<Movie> getGenre(int genreID);

	List<Movie> getListCommingSoonMovie();

	List<Movie> getListShowNowMovie();

	/**
	 * 
	 * @param movieID
	 */
	boolean updateStatusMovie(int movieID);

	/**
	 * 
	 * @param movieID
	 */
	Cast getCast(int movieID);

	/**
	 * 
	 * @param movieID
	 */
	Director getDirector(int movieID);

	/**
	 * 
	 * @param cast
	 */
	boolean addCast(Cast cast);

	/**
	 * 
	 * @param cast
	 */
	boolean updateCast(Cast cast);

	/**
	 * 
	 * @param director
	 */
	boolean addDirector(Director director);

	/**
	 * 
	 * @param director
	 */
	boolean updateDirector(Director director);

	/**
	 * 
	 * @param schedule
	 */
	boolean addSchedule(Schedule schedule);

	/**
	 * 
	 * @param scheduleID
	 */
	boolean updateStatusSchedule(int scheduleID);

	/**
	 * 
	 * @param movieID
	 */
	List<Schedule> getScheduleMovie(int movieID);

	/**
	 * 
	 * @param movieID
	 */
	List<Feedback> getFeedback(int movieID);

	/**
	 * 
	 * @param feedback
	 */
	boolean addFeedback(Feedback feedback);

}