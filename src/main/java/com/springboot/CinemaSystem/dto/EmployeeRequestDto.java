package com.springboot.CinemaSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private long ID;
    private String name;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String position;
    private String accessLevel;
    private long theaterid;
    private long managerid;
    private long roleid;

    public EmployeeRequestDto(long ID, String name, String email, Date dob, String phone, String address, String username, long roleid) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.roleid = roleid;
    }
}
