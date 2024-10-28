package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.service.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByID(int userID) {
		return null;
	}

	@Override
	public boolean changePassword(String password) {
		return false;
	}

	@Override
	public boolean deleteAccount(int accountID) {
		return false;
	}

	@Override
	public User login(Account account) {
		return null;
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public boolean addUser(User user) {
		return false;
	}
}