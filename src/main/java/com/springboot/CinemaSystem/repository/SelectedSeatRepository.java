package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.SelectedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectedSeatRepository extends JpaRepository<SelectedSeat, Long> {
    @Query("SELECT s FROM SelectedSeat s WHERE s.end <= :localDateTime AND s.status = 'pending'")
    List<SelectedSeat> getSelectedSeatByPending(@Param("localDateTime") LocalDateTime localDateTime);

}