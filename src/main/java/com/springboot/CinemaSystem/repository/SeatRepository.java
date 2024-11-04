package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
