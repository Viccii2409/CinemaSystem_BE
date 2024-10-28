package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.SeatTicketDao;

import java.util.List;

public class SeatTicketDaoImpl implements SeatTicketDao {

	@Override
	public boolean addSeatTicket(SeatTicket seatTicket) {
		return false;
	}

	@Override
	public SeatTicket getSeatTicketByID(int seatTicketID) {
		return null;
	}

	@Override
	public float getBasePriceFinal() {
		return 0;
	}

	@Override
	public boolean addBasePrice(BasePrice basePrice) {
		return false;
	}

	@Override
	public BasePrice getBasePriceByID(int basePriceID) {
		return null;
	}

	@Override
	public boolean addTypeUser(TypeUser typeUser) {
		return false;
	}

	@Override
	public TypeUser getTypeUserByID(int typeUserID) {
		return null;
	}

	@Override
	public List<TypeUser> getAllTypeUsers() {
		return List.of();
	}

	@Override
	public boolean addDayOfWeek(DayOfWeek dayOfWeek) {
		return false;
	}

	@Override
	public DayOfWeek getDayOfWeekByID(int dayOfWeekID) {
		return null;
	}

	@Override
	public List<DayOfWeek> getAllDaysOfWeek() {
		return List.of();
	}

	@Override
	public boolean addTimeFrame(TimeFrame timeFrame) {
		return false;
	}

	@Override
	public TimeFrame getTimeFrameByID(int timeFrameID) {
		return null;
	}

	@Override
	public List<TimeFrame> getAllTimeFrames() {
		return List.of();
	}

	@Override
	public boolean addSeatAvailability(SeatAvailability seatAvailability) {
		return false;
	}

	@Override
	public boolean updateSeatAvailability(SeatAvailability seatAvailability) {
		return false;
	}

	@Override
	public SeatAvailability getSeatAvailabilityByID(int seatAvailabilityID) {
		return null;
	}

	@Override
	public String checkSeatAvailability(int seatID, int showtimeID) {
		return "";
	}

	@Override
	public boolean addSeatReservation(SeatReservation seatReservation) {
		return false;
	}

	@Override
	public SeatReservation getReservationByID(int reservationID) {
		return null;
	}

	@Override
	public List<SeatReservation> getReservationsByCustomerID(int customerID) {
		return List.of();
	}
}