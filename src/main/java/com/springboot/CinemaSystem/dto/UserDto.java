package com.springboot.CinemaSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.CinemaSystem.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;
    private String address;
    private String email;
    private String phone;
    private String image;
    private LocalDateTime startDate;
    private String gender;
    private boolean status;
    private String username;
    private String password;
    private RoleDto role;
    private List<DiscountDto> discounts;
    private List<BookingDto> bookings;

    // customer
    private int points;
    private LevelDto level;
    private List<GenreDto> genres;

    // employee
    private String position;
    private boolean statusEmployee;
    private Date dayInWork;

    // admin
    private String accessLevel;

    // manager
    private String nameTheater;
    private List<UserDto> agents;

    // agent
    private String nameManager;

    private long theaterid;
    private long managerid;
    private long roleid;


    public static UserDto toEmployeeDto(Employee employee) {
        UserDto dto = new UserDto();
        dto.setId(employee.getID());
        dto.setUsername(employee.getAccount().getUsername());
        dto.setName(employee.getName());
        dto.setRole(RoleDto.toRoleDto2(employee.getRole()));
        dto.setStatusEmployee(employee.getStatusEmployee());
        return dto;
    }

    public static UserDto toUserVerifyDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getID());
        dto.setName(user.getName());
        dto.setImage(user.getImage());
        dto.setUsername(user.getAccount().getUsername());
        dto.setStatus(user.isStatus());
        dto.setRole(RoleDto.toRoleDto(user.getRole()));
        List<DiscountDto> discountDtos = user.getDiscount().stream()
                .map(entry -> DiscountDto.toDiscountDto(entry))
                .collect(Collectors.toList());
        dto.setDiscounts(discountDtos);
        return dto;
    }

    public static UserDto toUserCheck(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getID());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDob(user.getDob());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setUsername(user.getAccount().getUsername());
        dto.setRoleid(user.getRole().getID());
        return dto;
    }

    public static UserDto convertUsertoUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getID());
        dto.setName(user.getName());
        dto.setDob(user.getDob());
        dto.setAddress(user.getAddress());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setImage(user.getImage());
        dto.setStartDate(user.getStartDate());
        dto.setGender(user.getGender());
        dto.setStatus(user.isStatus());
        dto.setUsername(user.getAccount().getUsername());
        RoleDto roleDto = RoleDto.toRoleDto2(user.getRole());
        dto.setRole(roleDto);
        List<DiscountDto> discountDtos = user.getDiscount().stream()
                .map(entry -> DiscountDto.toDiscountDto(entry))
                .collect(Collectors.toList());
        dto.setDiscounts(discountDtos);
        List<BookingDto> bookingDtos = user.getBooking().stream()
                .map(entry -> BookingDto.toBookingDto2(entry))
                .collect(Collectors.toList());
        dto.setBookings(bookingDtos);
        return dto;
    }

    public static UserDto convertCustomertoUserDto(Customer customer) {
        User user = customer;
        UserDto dto = convertUsertoUserDto(user);
        dto.setPoints(customer.getPoints());
        dto.setLevel(LevelDto.toLevelDto(customer.getLevel()));
        List<GenreDto> genreDtos = customer.getGenre().stream()
                .map(entry -> GenreDto.toGenreDto(entry))
                .collect(Collectors.toList());
        dto.setGenres(genreDtos);
        return dto;
    }

    public static UserDto convertEmployeeToUserDto(Employee employee) {
        User user = employee;
        UserDto dto = convertUsertoUserDto(user);
        dto.setPosition(employee.getPosition());
        dto.setStatusEmployee(employee.getStatusEmployee());
        dto.setDayInWork(employee.getDayInWork());
        return dto;
    }

    public static UserDto convertAdminToUserDto (Admin admin) {
        Employee employee = admin;
        UserDto dto = convertEmployeeToUserDto(employee);
        dto.setAccessLevel(admin.getAccessLevel());
        return dto;
    }

    public static UserDto convertManagertoUserDto(Manager manager) {
        Employee employee = manager;
        UserDto dto = convertEmployeeToUserDto(employee);
        dto.setNameTheater(manager.getTheater().getName());
        List<UserDto> userDtos = manager.getAgents().stream()
                .map(entry -> toEmployeeDto(entry))
                .collect(Collectors.toList());
        dto.setAgents(userDtos);
        return dto;
    }

    public static UserDto convertAgenttoUserDto(Agent agent) {
        Employee employee = agent;
        UserDto dto = convertEmployeeToUserDto(employee);
        dto.setNameManager(agent.getManager().getName());
        return dto;
    }


}
