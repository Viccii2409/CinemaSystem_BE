package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.User;

public interface UserDao {

	public User login(Account account);
	public void updateUser(User user);
	public boolean addUser(User user);
	public User getUserByID(int userID);
	public boolean changePassword(String password);
	public boolean deleteAccount(int accountID);

}