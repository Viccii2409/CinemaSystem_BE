package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface PaymentDao {
	public boolean addPayment(Payment payment);
	public Payment getPaymentByID(int paymentID);
	public List<Payment> getAllPayments();
	public List<Payment> getPaymentsByCustomerID(int customerID);
	public boolean addPayQR(PayQR payQR);
	public PayQR getPayQRByID(int payQRID);
	public boolean addPayCard(PayCard payCard);
	public PayCard getPayCardByID(int payCardID);
	public boolean addPayCash(PayCash payCash);
	public PayCash getPayCashByID(int payCashID);
	public RevenueStat getRevenueStat(int type);


}