package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.PayOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PayOnlineRepository extends JpaRepository<PayOnline, Long> {
    PayOnline findByBarcode(String orderId);

    @Query("SELECT s FROM PayOnline s WHERE s.dateExpire <= :localDateTime AND s.status = 'pending'")
    List<PayOnline> getPayOnlineActive(@Param("localDateTime") LocalDateTime localDateTime);
}
