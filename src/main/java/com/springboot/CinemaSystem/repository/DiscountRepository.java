package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query("SELECT d FROM Discount d WHERE d.end < :currentDate AND d.status = true")
    List<Discount> getActiveDiscounts(@Param("currentDate") Date currentDate);

}