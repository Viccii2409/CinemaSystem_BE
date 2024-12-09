package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationID")
	private long ID;
	private String title;


	@Lob
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;
	private String date;

	@ManyToMany(mappedBy = "notification")
	@JsonIgnore
	private List<User> user;

}