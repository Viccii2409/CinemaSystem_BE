package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.TypeDiscount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDiscountDto {
    private long ID;
    private String name;

    public static TypeDiscountDto toTypeDiscountDto(TypeDiscount typeDiscount) {
        TypeDiscountDto dto = new TypeDiscountDto();
        dto.setID(typeDiscount.getID());
        dto.setName(typeDiscount.getName());
        return dto;
    }
}
