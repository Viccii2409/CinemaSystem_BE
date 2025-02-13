package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.TypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCustomerRepository extends JpaRepository<TypeCustomer, Long> {
}
