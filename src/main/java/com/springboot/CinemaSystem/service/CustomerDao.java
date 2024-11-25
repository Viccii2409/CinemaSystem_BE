package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.Level;

import java.util.Date;
import java.util.List;

public interface CustomerDao {
	public boolean addCustomer(Customer customer);
	public boolean updateCustomer(Customer customer);
	public Customer getCustomerID(int customerID);
	public boolean earnPoints(int customerID, int points);
	public boolean redeemPoints(int customerID, int points);

	public boolean addLevel(Level level);
	public boolean updateLevel(Level level);
	public Level getLevelByID(int levelID);
	public List<Level> getAllLevels();

	public float getCustomerStat(Date startDate, Date endDate);


}