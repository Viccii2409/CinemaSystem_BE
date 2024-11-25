package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountAddDto {
    private long id;
    private String name;
    private long typeDiscountid;
    private float reducedValue;
    private String discountCode;
    private String start;
    private String end;
    private String description;
    private boolean status;
}