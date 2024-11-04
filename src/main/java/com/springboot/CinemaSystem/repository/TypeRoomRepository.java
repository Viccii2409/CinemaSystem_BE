package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long> {
}
