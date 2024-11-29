package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.User;

import java.util.List;

public interface UserDao {
	public User getUserByID(long userID);
	public Customer getCustomerById(long id);
	public void updateCustomer(Customer customer);
	public List<UserDto> getAllCustomers();

	public User login(Account account);
	public void updateUser(User user) throws Exception;
	public boolean addUser(User user);
	public boolean changePassword(String password);
	public boolean deleteAccount(int accountID);


}