package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.BasePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePriceRepository extends JpaRepository<BasePrice, Long> {
    public BasePrice findTopByOrderByIDDesc();
}
