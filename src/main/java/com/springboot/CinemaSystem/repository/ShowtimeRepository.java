package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Showtime;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'upcoming' " +
            "WHERE ((s.startTime > :currentTime AND s.date = :currentDate) " +
            "OR s.date > :currentDate) " +
            "AND (s.action IS NULL OR s.action <> 'upcoming')")
    public void updateActionToUpcoming(Date currentDate, Time currentTime);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'running' " +
            "WHERE s.startTime <= :currentTime " +
            "AND s.endTime >= :currentTime " +
            "AND s.date = :currentDate " +
            "AND (s.action IS NULL OR s.action <> 'running')")
    public void updateActionToRunning(Date currentDate, Time currentTime);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'ended' " +
            "WHERE ((s.endTime < :currentTime AND s.date = :currentDate) " +
            "OR s.date < :currentDate) " +
            "AND (s.action IS NULL OR s.action <> 'ended')")
    public void updateActionToEnded(Date currentDate, Time currentTime);
}