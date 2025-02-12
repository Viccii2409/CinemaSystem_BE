package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);
    List<Movie> findByReleaseDateAfter(LocalDate date);
    List<Movie> findByStatus(boolean status);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > :today and status=true ORDER BY m.releaseDate ASC")
    List<Movie> findMoviesComingSoon(@Param("today") LocalDate today);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate <= :today and status=true ORDER BY m.releaseDate ASC")
    List<Movie> findMoviesNowShowing(@Param("today") LocalDate today);
    @Query("SELECT m FROM Movie m JOIN m.genre g WHERE g.name = :genreName")
    List<Movie> findByGenreName(String genreName);

    List<Movie> findByStatusAndReleaseDateGreaterThanEqual(boolean status, LocalDate releaseDate); // Lọc phim theo trạng thái và ngày khởi chiếu
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genre g WHERE g.id IN :genreId")
    List<Movie> findMoviesByGenres(List<Long> genreId);

}
