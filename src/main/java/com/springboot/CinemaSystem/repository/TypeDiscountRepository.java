package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.TypeDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDiscountRepository extends JpaRepository<TypeDiscount, Long> {
}
