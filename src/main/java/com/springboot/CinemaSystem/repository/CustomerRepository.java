package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c.genre FROM Customer c WHERE c.id = :customerId")
    List<Genre> findGenresByCustomerId(@Param("customerId") Long customerId);
}