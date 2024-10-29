package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CUSTOMERSERVICE")
public class CustomerService extends Employee {

	private String assignedTasks;

}