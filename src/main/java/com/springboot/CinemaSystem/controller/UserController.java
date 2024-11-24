package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.CustomerDao;
import com.springboot.CinemaSystem.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CustomerDao customerDao;
    @GetMapping("/all-customers")
    public List<UserDto> getAllCustomers() {
        List<UserDto> customers = userDao.getAllCustomers();
        if ( customers.isEmpty()) {
            throw new NotFoundException("No customers found.");
        }
        return customers;
    }
}
