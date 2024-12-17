package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBarcode(String barcode);
}
