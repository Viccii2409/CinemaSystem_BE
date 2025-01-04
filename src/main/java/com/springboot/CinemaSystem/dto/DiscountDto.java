package com.springboot.CinemaSystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.CinemaSystem.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
    private long id;
    private String name;
    private TypeDiscountDto typeDiscount;
    private float reducedValue;
    private String discountCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;
    private String description;
    private String image;
    private boolean status;

    private long typeDiscountid;

    private boolean used;

    public static DiscountDto toDiscountDto(Discount discount) {
        DiscountDto dto = new DiscountDto();
        dto.setId(discount.getID());
        dto.setName(discount.getName());
        dto.setTypeDiscount(TypeDiscountDto.toTypeDiscountDto(discount.getTypeDiscount()));
        dto.setReducedValue(discount.getReducedValue());
        dto.setDiscountCode(discount.getDiscountCode());
        dto.setStart(discount.getStart());
        dto.setEnd(discount.getEnd());
        dto.setDescription(discount.getDescription());
        dto.setImage(discount.getImage());
        dto.setStatus(discount.isStatus());
        dto.setUsed(discount.getPayment().size() > 0);
        return dto;
    }
}
