package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository
public interface TimeFrameRepository extends JpaRepository<TimeFrame, Long> {
    @Query("SELECT t FROM TimeFrame t WHERE :time >= t.timeStart AND :time < t.timeEnd")
    TimeFrame findByTimeInRange(@Param("time") Time time);
}
