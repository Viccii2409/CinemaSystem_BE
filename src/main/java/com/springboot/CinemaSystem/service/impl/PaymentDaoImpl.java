package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.PaymentDao;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public boolean addPayment(Payment payment) {
		return false;
	}

	@Override
	public Payment getPaymentByID(int paymentID) {
		return null;
	}

	@Override
	public List<Payment> getAllPayments() {
		return List.of();
	}

	@Override
	public List<Payment> getPaymentsByCustomerID(int customerID) {
		return List.of();
	}

	@Override
	public boolean addPayQR(PayQR payQR) {
		return false;
	}

	@Override
	public PayQR getPayQRByID(int payQRID) {
		return null;
	}

	@Override
	public boolean addPayCard(PayCard payCard) {
		return false;
	}

	@Override
	public PayCard getPayCardByID(int payCardID) {
		return null;
	}

	@Override
	public boolean addPayCash(PayCash payCash) {
		return false;
	}

	@Override
	public PayCash getPayCashByID(int payCashID) {
		return null;
	}

	@Override
	public RevenueStat getRevenueStat(int type) {
		return null;
	}
}