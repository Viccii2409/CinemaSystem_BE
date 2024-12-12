package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private long showtimeid;
    private long userid;
    private long discountid;
    private long totalPrice;
    private long discountPrice;
    private long amount;
    private String typePay;
    private String typeBooking;
    private float received;
    private float moneyReturned;
    private Map<Long, Map<Long, Integer>> paytypecustomer;
    private List<TicketDto> ticket;
}
