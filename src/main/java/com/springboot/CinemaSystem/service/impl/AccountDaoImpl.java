package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.repository.AccountRepository;
import com.springboot.CinemaSystem.repository.UserRepository;
import com.springboot.CinemaSystem.service.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    // Tìm Account theo username
    public Account findAccountByUsername(String email) {
        return accountRepository.findByEmail(email);
    }

    // Tìm User qua userID
    public User findUserByUserID(long userID) {
        return userRepository.findById(userID).orElse(null);
    }
}
