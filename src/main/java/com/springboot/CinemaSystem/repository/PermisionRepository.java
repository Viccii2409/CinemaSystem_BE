package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisionRepository extends JpaRepository<Permission, Long> {
}
