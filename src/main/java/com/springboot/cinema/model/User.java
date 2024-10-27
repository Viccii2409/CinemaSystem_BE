package com.example.cinema.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long id;

    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "MidName")
    private String midName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Gender",nullable = false)
    private Boolean gender;

    @Column(name = "Dob")
    private Date dob;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Image")
    private String image;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "Points", nullable = false)
    private Integer points;

    @Column(name = "Status", nullable = false)
    private Boolean status;

    @Column(name = "Privileges")
    private String privileges;

    @Column(name = "LastLogin")
    private String lastLogin;

    @Column(name = "AssignedTasks")
    private String assignedTasks;

    @Column(name = "WorkStation")
    private Integer workStation;

    @Column(name = "TicketsSold")
    private Integer ticketsSold;

    @Column(name = "ManagedEmployees")
    private Integer managedEmployees;

    @ManyToOne
    @JoinColumn(name = "LevelID", nullable = false)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "PositionID", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "TheaterID")
    private Theater theater;

    // Quan hệ với bảng Account
    @OneToOne
    @JoinColumn(name = "AccountID")
    private Account account;

    // Quan hệ với bảng TicketBought
    @OneToMany(mappedBy = "user")
    private List<TicketBought> ticketsBought;

    @OneToMany(mappedBy = "user")
    private List<SeatReservation> seatsReservation;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @OneToMany(mappedBy = "user")
    private List<UserNotification> userNotifications;

    @OneToMany(mappedBy = "user")
    private List<UserDiscount>userDiscounts;

    @OneToMany(mappedBy = "user")
    private List<UserGenre> userGenres;
    // Các getter và setter

}
