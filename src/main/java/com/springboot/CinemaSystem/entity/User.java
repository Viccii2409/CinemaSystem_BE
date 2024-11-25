package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

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
	private boolean gender;
	private Date dob;
	private String address;
	private String email;
	private String phone;
	private String image;
	private Date startDate;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "user-account")
	private Account account;

	@Embedded
	private Name name;

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