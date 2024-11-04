package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageID")
	private long ID;
	private String link;
	private boolean type;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonIgnore
	private Movie movie;

}