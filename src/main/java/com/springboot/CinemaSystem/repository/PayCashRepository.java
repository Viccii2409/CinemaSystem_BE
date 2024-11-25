package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.PayCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayCashRepository extends JpaRepository<PayCash, Long> {
}
