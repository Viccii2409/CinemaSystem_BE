package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    Optional<Feedback> findByBooking_ID(long bookingID);
    @Query("SELECT f FROM Feedback f WHERE f.movie.id = :movieId")
    List<Feedback> findByMovieId(@Param("movieId") long movieId);
    // Kiểm tra xem đã có Feedback cho booking và movie này chưa
    boolean existsByBooking_ID(long bookingID);
}
