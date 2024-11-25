package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.Level;
import com.springboot.CinemaSystem.repository.UserRepository;
import com.springboot.CinemaSystem.service.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDaoImpl implements CustomerDao {
	@Override
	public boolean addCustomer(Customer customer) {
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		return false;
	}

	@Override
	public Customer getCustomerID(int customerID) {
		return null;
	}



	@Override
	public boolean earnPoints(int customerID, int points) {
		return false;
	}

	@Override
	public boolean redeemPoints(int customerID, int points) {
		return false;
	}

	@Override
	public boolean addLevel(Level level) {
		return false;
	}

	@Override
	public boolean updateLevel(Level level) {
		return false;
	}

	@Override
	public Level getLevelByID(int levelID) {
		return null;
	}

	@Override
	public List<Level> getAllLevels() {
		return List.of();
	}

	@Override
	public float getCustomerStat(Date startDate, Date endDate) {
		return 0;
	}
}