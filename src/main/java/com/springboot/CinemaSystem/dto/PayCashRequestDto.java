package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayCashRequestDto {
    private long showtimeid;
    private long agentid;
    private long customerid;
    private long discountid;
    private float totalPrice;
    private float discountPrice;
    private float amount;
    private float received;
    private float moneyReturned;
    private Map<Long, Map<Long, Integer>> paytypecustomer;
    private List<TicketRequestDto> ticket;
}