package com.springboot.CinemaSystem.dto;

import java.util.Date;

public class LoginResponse {
    private Long id;
    private String name;
    private String address;
    private Date dob;
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String user_type;

    public LoginResponse(Long id,String name, String address, Date dob, String email, String password,String gender, String phone, String user_type) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.password=password;
        this.gender = gender;
        this.phone = phone;
        this.user_type = user_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String privileges) {
        this.user_type = user_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
