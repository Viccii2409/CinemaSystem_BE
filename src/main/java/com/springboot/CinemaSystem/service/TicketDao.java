package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface TicketDao {
	public void updateAllSelectedSeat();
	public void updateStatusPayOnlineToExpired();
	public List<TypeCustomer> getAllTypeCustomer();

	public TypeCustomer getTypeCustomerById(long id);
	public void updateTypeCustomer(TypeCustomer typeCustomer);

	public long addSelectedSeat(SelectedSeat selectedSeat);
	public SelectedSeat getSelectedSeatByID(long selectedSeatID);
	public boolean updateStatusToExpired(long id);

	public Booking addBooking(Booking booking);
	public Booking updateBooking(Booking booking);
	public PayCash addPayCash(PayCash payCash);
	public void addPayTypeCustomer(List<PayTypeCustomer> payTypeCustomers);
	public Booking getBookingById(long id);

	public Payment updatePayment(Payment paymentNew);
	public Payment getPaymentByBarcode(String barcode);
	public PayOnline addPayOnline(PayOnline payOnline);
	public void updateSelectSeatStatusToExpired(long showtimeID, long userid, long seatid);
	public Booking getBookingByBarcode(String barcode);

}