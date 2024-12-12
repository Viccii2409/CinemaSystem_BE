package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.LoginResponse;
import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.AccountDao;
import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserDao userDao;
    private AccountDao accountDao;

    @Autowired
    public UserController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
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

//    @GetMapping("/inforaccount/{id}")
//    public CustomerDto getCustomerInfor(@PathVariable("id") long id) {
//        Customer customer = userDao.getCustomerById(id);
//        return customer.toCustomerDto2();
//    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Trả về trang đăng nhập (login.html)
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        // Kiểm tra thông tin đăng nhập
        Account foundAccount = accountDao.findAccountByUsername(account.getEmail());
        if (foundAccount != null && foundAccount.getPassword().equals(account.getPassword())) {
            // Trả về thông tin user và loại tài khoản (user_type)
            return ResponseEntity.ok().body(new LoginResponse( foundAccount.getID(),foundAccount.getUser().getName(), foundAccount.getUser().getAddress(),
                            foundAccount.getUser().getDob(),foundAccount.getEmail(),foundAccount.getPassword(),foundAccount.getUser().getGender(),
                    foundAccount.getUser().getPhone(),
                    foundAccount.getUser().getPrivileges()
                   ));

        } else {
            // Trả về lỗi nếu đăng nhập không thành công
            return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody User request) {
        try {
            userDao.updateUser(request);
            return ResponseEntity.ok("Thông tin cá nhân được cập nhật thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cập nhật thất bại: " + e.getMessage());
        }
    }
}