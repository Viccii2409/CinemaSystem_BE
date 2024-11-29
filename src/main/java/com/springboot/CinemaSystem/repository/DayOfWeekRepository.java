package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {
    @Query("SELECT d FROM DayOfWeek d WHERE :day BETWEEN d.dayStart AND d.dayEnd")
    DayOfWeek findByDayInRange(@Param("day") int day);
    DayOfWeek findByName(String name);
}
