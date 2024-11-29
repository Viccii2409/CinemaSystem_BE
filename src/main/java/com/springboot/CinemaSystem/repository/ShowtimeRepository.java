package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Showtime;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'upcoming' " +
            "WHERE ((s.startTime > :currentTime AND s.date = :currentDate) " +
            "OR s.date > :currentDate) " +
            "AND (s.action IS NULL OR s.action <> 'upcoming')")
    public void updateActionToUpcoming(@Param("currentDate") Date currentDate, @Param("currentTime") Time currentTime);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'running' " +
            "WHERE s.startTime <= :currentTime " +
            "AND s.endTime >= :currentTime " +
            "AND s.date = :currentDate " +
            "AND (s.action IS NULL OR s.action <> 'running')")
    public void updateActionToRunning(@Param("currentDate") Date currentDate, @Param("currentTime") Time currentTime);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Showtime s SET s.action = 'ended', s.status = false " +
            "WHERE ((s.endTime < :currentTime AND s.date = :currentDate) " +
            "OR s.date < :currentDate) " +
            "AND (s.action IS NULL OR s.action <> 'ended')")
    public void updateActionToEnded(@Param("currentDate") Date currentDate, @Param("currentTime") Time currentTime);




//    public List<Showtime> findByRoomIdAndDate(long roomId, LocalDate date);
//    public List<Showtime> findByMovieStatusAndDateAfter(int movieStatus, LocalDate date);

    // check trùng
    @Query("SELECT s FROM Showtime s WHERE s.room.ID = :roomId " +
            "AND s.date = :date " +
            "AND ((:startTime BETWEEN s.startTime AND s.endTime) " +
            "OR (:endTime BETWEEN s.startTime AND s.endTime) " +
            "OR (s.startTime BETWEEN :startTime AND :endTime))")
    public List<Showtime> findConflictingShowtimes(@Param("roomId") long roomId,
                                            @Param("date") Date date,
                                            @Param("startTime") Time startTime,
                                            @Param("endTime") Time endTime);
    // Ẩn hoặc mở lịch chiếu theo yêu cầu người dùng
    @Transactional
    @Modifying
    @Query("UPDATE Showtime s SET s.status = :status WHERE s.ID = :showtimeId")
    void toggleShowtimeStatus(@Param("showtimeId") long showtimeId, @Param("status") boolean status);

    // Tìm các lịch chiếu theo trạng thái phim
    public List<Showtime> findByMovieStatusAndDateAfter(boolean status, LocalDate date);


// cập nhật trạng thái theo phim

    @Modifying
    @Transactional
    @Query(value = "UPDATE showtime s " +
            "JOIN movie m ON m.movieid = s.movieid " +
            "SET s.status = 0 " +
            "WHERE s.movieid = :movieId AND m.status = 0", nativeQuery = true)
    void hideShowtimesByMovieStatus(@Param("movieId") long movieId);

//    // Tìm các lịch chiếu theo theaterId
//    List<Showtime> findByTheaterId(long theaterId);




}