package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Thêm các phương thức tùy chỉnh nếu cần
}