package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.TypeSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSeatRepository extends JpaRepository<TypeSeat, Long> {
}
