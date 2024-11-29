package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayOnlineRequestDto {
    private long showtimeid;
    private long agentid;
    private long customerid;
    private long discountid;
    private long totalPrice;
    private long discountPrice;
    private long amount;
    private Map<Long, Map<Long, Integer>> paytypecustomer;
    private List<TicketRequestDto> ticket;
}
