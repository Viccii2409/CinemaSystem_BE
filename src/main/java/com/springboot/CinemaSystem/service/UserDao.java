package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.User;

import java.util.List;

public interface UserDao {
	public Customer getCustomerById(long id);
	public void updateCustomer(Customer customer);

	public User login(Account account);
	public void updateUser(User user);
	public boolean addUser(User user);
	public User getUserByID(int userID);
	public boolean changePassword(String password);
	public boolean deleteAccount(int accountID);
	public List<UserDto> getAllCustomers();
}