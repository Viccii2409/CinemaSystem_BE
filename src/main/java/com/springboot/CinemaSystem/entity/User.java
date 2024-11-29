package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.sql.Date;
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
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

	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "levelID", referencedColumnName = "levelID")
	private Level level;

	@ManyToMany
	@JoinTable(
			name = "user_notification", // Tên bảng trung gian
			joinColumns = @JoinColumn(name = "userID"), // Khóa ngoại từ bảng User
			inverseJoinColumns = @JoinColumn(name = "notificationID") // Khóa ngoại từ bảng Notification
	)
	private List<Notification> notification;
	@Column(insertable = false, updatable = false)
	private String user_type;
}