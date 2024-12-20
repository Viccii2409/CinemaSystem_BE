package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // 1. Thống kê tổng doanh thu theo thời gian
    @Query("SELECT DATE(b.date) AS bookingDate, SUM(p.amount) AS totalRevenue " +
            "FROM Booking b JOIN b.payment p " +
            "WHERE b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(b.date) " +
            "ORDER BY DATE(b.date)")
    List<Object[]> revenueByDate(@Param("startDate") LocalDateTime startDate,
                                 @Param("endDate") LocalDateTime endDate);


    // 2. Thống kê doanh thu theo rạp và thời gian
    @Query("SELECT t.name AS theaterName, DATE(b.date) AS bookingDate, SUM(p.amount) AS totalRevenue " +
            "FROM Booking b JOIN b.showtime s JOIN s.room r JOIN r.theater t JOIN b.payment p " +
            "WHERE t.id = :theaterId AND b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY t.name, DATE(b.date) " +
            "ORDER BY DATE(b.date)")
    List<Object[]> revenueByTheater(@Param("theaterId") Long theaterId,
                                    @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate);


    // 3. Thống kê doanh thu theo phim và thời gian
    @Query("SELECT m.title AS movieTitle, DATE(b.date) AS bookingDate, SUM(p.amount) AS totalRevenue " +
            "FROM Booking b JOIN b.showtime s JOIN s.movie m JOIN b.payment p " +
            "WHERE m.id = :movieId AND b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY m.title, DATE(b.date) " +
            "ORDER BY DATE(b.date)")
    List<Object[]> revenueByMovie(@Param("movieId") Long movieId,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate);


    // Thống kê doanh thu theo tháng/năm
    @Query("SELECT new map(MONTH(b.date) AS month, YEAR(b.date) AS year, SUM(p.amount) AS totalRevenue) " +
            "FROM Booking b JOIN b.payment p " +
            "WHERE b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(b.date), MONTH(b.date)")
    List<Map<String, Object>> getRevenueByMonthOrYear(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.amount) " +
            "FROM Booking b JOIN b.payment p " +
            "WHERE b.date BETWEEN :startDate AND :endDate")
    Double getTotalRevenueByMonthOrYear(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Thống kê doanh thu tất cả phim
    @Query("SELECT new map(m.title AS movieTitle, SUM(p.amount) AS totalRevenue) " +
            "FROM Booking b JOIN b.payment p JOIN b.showtime s JOIN s.movie m " +
            "WHERE b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY m.id, m.title")
    List<Map<String, Object>> getRevenueByMovie(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.amount) " +
            "FROM Booking b JOIN b.payment p JOIN b.showtime s JOIN s.movie m " +
            "WHERE b.date BETWEEN :startDate AND :endDate")
    Double getTotalRevenueByMovie(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Thống kê doanh thu tất cả rạp
    @Query("SELECT new map(t.name AS theaterName, SUM(p.amount) AS totalRevenue) " +
            "FROM Booking b JOIN b.payment p JOIN b.showtime s JOIN s.room r JOIN r.theater t " +
            "WHERE b.date BETWEEN :startDate AND :endDate " +
            "GROUP BY t.id, t.name")
    List<Map<String, Object>> getRevenueByTheater(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(p.amount) " +
            "FROM Booking b JOIN b.payment p JOIN b.showtime s JOIN s.room r JOIN r.theater t " +
            "WHERE b.date BETWEEN :startDate AND :endDate")
    Double getTotalRevenueByTheater(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);


    // Lấy top 3 phim có tổng doanh thu cao nhất trong 3 tháng gần nhất với trạng thái phim = true
    @Query("SELECT new map(m.id AS movieId, m.title AS movieTitle, m.image AS movieImage, SUM(p.totalPrice) AS totalRevenue) " +
            "FROM Booking b " +
            "JOIN b.payment p " +
            "JOIN b.showtime s " +
            "JOIN s.movie m " +
            "WHERE b.date BETWEEN :startDate AND :endDate AND m.status = true " +
            "GROUP BY m.id, m.title, m.image " +
            "ORDER BY totalRevenue DESC")
    List<Map<String, Object>> getTop3Movies(@Param("startDate") LocalDateTime startDate,
                                                                  @Param("endDate") LocalDateTime endDate);
}