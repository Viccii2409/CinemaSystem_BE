package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.TicketBought;

import java.util.List;

public interface TicketBoughtDao {
	public TicketBought getTicketBoughtByID(int ticketBoughtID);
	public boolean addTicketBought(TicketBought ticketBought);
	public List<TicketBought> getTicketBoughtByCustomer(int customerID);

}