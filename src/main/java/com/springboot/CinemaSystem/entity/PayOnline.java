package com.springboot.CinemaSystem.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PAYONLINE")
public class PayOnline extends Payment{
    private LocalDateTime dateExpire;
    private String status;  // pending, confirmed, expired

}
