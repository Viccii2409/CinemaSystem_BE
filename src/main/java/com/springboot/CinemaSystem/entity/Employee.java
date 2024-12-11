package com.springboot.CinemaSystem.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springboot.CinemaSystem.dto.*;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {
	private String position;
	private Date dayInWork;
	private boolean statusEmployee;

	@PrePersist
	private void prePerisistDayInWork() {
		if(this.dayInWork == null) {
			long currentTimeMillis = System.currentTimeMillis();
			this.dayInWork = new Date(currentTimeMillis);
		}
	}
}