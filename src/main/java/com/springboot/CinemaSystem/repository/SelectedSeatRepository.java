package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.SelectedSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SelectedSeatRepository extends JpaRepository<SelectedSeat, Long> {
    @Query("SELECT s FROM SelectedSeat s WHERE s.end <= :localDateTime AND s.status = 'pending'")
    List<SelectedSeat> getSelectedSeatByPending(@Param("localDateTime") LocalDateTime localDateTime);

    @Modifying
    @Transactional
    @Query("UPDATE SelectedSeat s SET s.status = 'expired' WHERE s.showtime.id = :showtimeID AND s.user.id = :userID AND s.seat.id = :seatID")
    void updateSeatStatusToExpired(@Param("showtimeID") long showtimeID,
                                   @Param("userID") long userID,
                                   @Param("seatID") long seatID);

}