package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.User;

public interface AccountDao {
    public Account findAccountByUsername(String email);
    public User findUserByUserID(long userID);
}
