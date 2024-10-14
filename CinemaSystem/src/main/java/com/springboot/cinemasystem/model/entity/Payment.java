package com.springboot.cinemasystem.model.entity;

public class Payment {

	Ticket ticket;
	Discount discount;
	private int paymentID;
	private boolean payMethod;
	private int amount;

	public int getPaymentID() {
		return this.paymentID;
	}

	/**
	 * 
	 * @param paymentID
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public boolean getPayMethod() {
		return this.payMethod;
	}

	/**
	 * 
	 * @param payMethod
	 */
	public void setPayMethod(boolean payMethod) {
		this.payMethod = payMethod;
	}

	public int getAmount() {
		return this.amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

}