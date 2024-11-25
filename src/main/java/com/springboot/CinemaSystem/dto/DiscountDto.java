package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private long id;
    private String name;
    private TypeDiscountDto typeDiscount;
    private float reducedValue;
    private String discountCode;
    private Date start;
    private Date end;
    private String description;
    private String image;
    private boolean status;

    public DiscountDto(long id) {
        this.id = id;
    }
}
