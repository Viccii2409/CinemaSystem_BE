package com.springboot.cinemasystem.service.impl;

import java.util.List;

import com.springboot.cinemasystem.model.entity.Account;
import com.springboot.cinemasystem.model.entity.Role;
import com.springboot.cinemasystem.model.entity.User;
import com.springboot.cinemasystem.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean signup(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatusUser(int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatusRole(int roleID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(int userID, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getRole(int roleID) {
		// TODO Auto-generated method stub
		return null;
	}

}