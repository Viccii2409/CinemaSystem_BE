package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
