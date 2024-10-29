package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.TicketBought;
import com.springboot.CinemaSystem.service.TicketBoughtDao;

import java.util.List;

public class TicketBoughtDaoImpl implements TicketBoughtDao {

	@Override
	public TicketBought getTicketBoughtByID(int ticketBoughtID) {
		return null;
	}

	@Override
	public boolean addTicketBought(TicketBought ticketBought) {
		return false;
	}

	@Override
	public List<TicketBought> getTicketBoughtByCustomer(int customerID) {
		return List.of();
	}
}