package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface SeatTicketDao {
	public boolean addSeatTicket(SeatTicket seatTicket);
	public SeatTicket getSeatTicketByID(int seatTicketID);
	public float getBasePriceFinal();
	public boolean addBasePrice(BasePrice basePrice);
	public BasePrice getBasePriceByID(int basePriceID);
	public boolean addTypeUser(TypeUser typeUser);
	public TypeUser getTypeUserByID(int typeUserID);
	public List<TypeUser> getAllTypeUsers();
	public boolean addDayOfWeek(DayOfWeek dayOfWeek);
	public DayOfWeek getDayOfWeekByID(int dayOfWeekID);
	public List<DayOfWeek> getAllDaysOfWeek();
	public boolean addTimeFrame(TimeFrame timeFrame);
	public TimeFrame getTimeFrameByID(int timeFrameID);
	public List<TimeFrame> getAllTimeFrames();
	public boolean addSeatAvailability(SeatAvailability seatAvailability);
	public boolean updateSeatAvailability(SeatAvailability seatAvailability);
	public SeatAvailability getSeatAvailabilityByID(int seatAvailabilityID);
	public String checkSeatAvailability(int seatID, int showtimeID);
	public boolean addSeatReservation(SeatReservation seatReservation);
	public SeatReservation getReservationByID(int reservationID);
	public List<SeatReservation> getReservationsByCustomerID(int customerID);


}