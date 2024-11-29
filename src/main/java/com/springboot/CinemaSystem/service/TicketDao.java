package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.dto.TypeDiscountDto;
import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface TicketDao {
	public List<TimeFrame> getAllTimeFrames();
	public List<DayOfWeek> getAllDayOfWeeks();
	public List<TypeCustomer> getAllTypeCustomer();
	public BasePrice getBasePrice();

	public TypeCustomer getTypeCustomerById(long id);
	public void updateTypeCustomer(TypeCustomer typeCustomer);

	public DayOfWeek getDayOfWeekById(Long key);
	public void updateDayOfWeek(DayOfWeek dayOfWeek);

	public TimeFrame getTimeFrameById(Long key);
	public void updateTimeFrame(TimeFrame timeFrame);

	public void updateAllSelectedSeat();
	public long addSelectedSeat(SelectedSeat selectedSeat);
	public SelectedSeat getSelectedSeatByID(long selectedSeatID);
	public boolean updateStatusToExpired(long id);

	public Booking addBooking(Booking booking);
	public PayCash addPayCash(PayCash payCash);
	public void addPayTypeCustomer(List<PayTypeCustomer> payTypeCustomers);
	public void addTicket(List<Ticket> tickets);
	public Booking getBookingById(long id);

}