package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.PayTypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayTypeCustomerRepository extends JpaRepository<PayTypeCustomer, Long> {
}
