package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {
    Optional<Trailer> findByMovieId(Long movieId);
}