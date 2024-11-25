package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageID")
	private long ID;
	private String link;
	private boolean type;

	@ManyToOne
	@JoinColumn(name = "movieID")
	@JsonBackReference
	private Movie movie;

}