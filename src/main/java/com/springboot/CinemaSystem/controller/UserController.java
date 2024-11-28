package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") long id) {
        Customer customer = userDao.getCustomerById(id);
        return customer.toCustomerDto();
    }

    @GetMapping("/all-customers")
    public List<UserDto> getAllCustomers() {
        List<UserDto> customers = userDao.getAllCustomers();
        if ( customers.isEmpty()) {
            throw new NotFoundException("No customers found.");
        }
        return customers;
    }
}