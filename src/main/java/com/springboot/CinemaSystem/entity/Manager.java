package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {

	private int managedEmployees;

}