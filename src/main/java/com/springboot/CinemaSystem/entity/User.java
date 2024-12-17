package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
	@Column(nullable = false, unique = true)
	private String email;
	private String phone;
	private String image;
	private LocalDateTime startDate;
	private String name;
	private boolean status;
	private String verificationCode;
	@Embedded
	private Account account;
//    @Column(insertable = false, updatable = false)	// không được phép thay đổi (update) hoặc chèn (insert) thông qua các thao tác của JPA
//    private String userType;

	@Transient
	private double totalSpending;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleID")
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Booking> booking;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SelectedSeat> selectedSeats;

	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_discount",
			joinColumns = @JoinColumn(name = "userID"),
			inverseJoinColumns = @JoinColumn(name = "discountID")
	)
	private List<Discount> discount;

	@PrePersist
	private void prePersistDate() {
		if (this.startDate == null) {
			this.startDate = LocalDateTime.now();
		}
	}
}