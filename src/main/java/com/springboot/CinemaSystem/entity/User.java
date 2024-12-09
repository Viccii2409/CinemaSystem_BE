package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	private long ID;
	private String gender;
	private Date dob;
	private String address;
	private String email;
	private String phone;
	private String image;
	private Date startDate;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Account account;
	private String privileges;
    @Column(insertable = false, updatable = false)
    private String user_type;

	private String name;

	@ManyToMany
	@JoinTable(
			name = "user_notification", // Tên bảng trung gian
			joinColumns = @JoinColumn(name = "userID"), // Khóa ngoại từ bảng User
			inverseJoinColumns = @JoinColumn(name = "notificationID")
	)
	private List<Notification> notification;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<SelectedSeat> selectedSeats;
}