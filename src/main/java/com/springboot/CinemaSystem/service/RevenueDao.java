package com.springboot.CinemaSystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RevenueDao {
    public List<Map<String, Object>> getRevenueByTime(LocalDateTime startDate, LocalDateTime endDate);
    public List<Map<String, Object>> getRevenueByTheater(Long theaterId, LocalDateTime startDate, LocalDateTime endDate);
    public List<Map<String, Object>> getRevenueByMovie(Long movieId, LocalDateTime startDate, LocalDateTime endDate);

    List<Map<String, Object>> getRevenueByMonthOrYear(LocalDateTime startDate, LocalDateTime endDate);
    Double getTotalRevenueByMonthOrYear(LocalDateTime startDate, LocalDateTime endDate);

    List<Map<String, Object>> getRevenueByMovie(LocalDateTime startDate, LocalDateTime endDate);
    Double getTotalRevenueByMovie(LocalDateTime startDate, LocalDateTime endDate);

    List<Map<String, Object>> getRevenueByTheater(LocalDateTime startDate, LocalDateTime endDate);
    Double getTotalRevenueByTheater(LocalDateTime startDate, LocalDateTime endDate);
}
