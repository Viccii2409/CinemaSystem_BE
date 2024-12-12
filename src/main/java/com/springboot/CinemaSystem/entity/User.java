package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import com.springboot.CinemaSystem.dto.PermissionDto;
import com.springboot.CinemaSystem.dto.RoleDto;
import com.springboot.CinemaSystem.dto.UserRegisterDto;
import com.springboot.CinemaSystem.dto.UserVerifyDto;
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
	private String email;
	private String phone;
	private String image;
	private LocalDateTime startDate;
	private String name;
	private boolean status;
	@Embedded
	private Account account;
//    @Column(insertable = false, updatable = false)	// không được phép thay đổi (update) hoặc chèn (insert) thông qua các thao tác của JPA
//    private String userType;

	@Transient
	private double totalSpending;

	@ManyToOne
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

//	public User(String name, String gender, Date dob, String address, String email, Account account) {
//		this.name = name;
//		this.gender = gender;
//		this.dob = dob;
//		this.address = address;
//		this.email = email;
//		this.account = account;
//	}

	@PrePersist
	private void prePersistDate() {
		if (this.startDate == null) {
			this.startDate = LocalDateTime.now();
		}
	}

	public UserVerifyDto toUserVerifyDto() {
		return new UserVerifyDto(
				this.ID,
				this.name,
				this.dob,
				this.address,
				this.email,
				this.phone,
				this.image,
				this.status,
				new RoleDto(
						this.role.getID(),
						this.role.getName(),
						this.role.getUsers().size(),
						this.role.getPermission().stream()
						.map(entry -> new PermissionDto(entry.getID(), entry.getName()))
						.collect(Collectors.toList())
				),
				this.discount.stream().map(entry -> entry.toDiscountDto()).collect(Collectors.toList())
		);
	}
}