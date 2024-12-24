package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.repository.BookingRepository;
import com.springboot.CinemaSystem.service.MovieDao;
import com.springboot.CinemaSystem.service.RevenueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RevenueDaoImpl implements RevenueDao {
    @Autowired
    private BookingRepository bookingRepository;

    // Thống kê doanh thu theo ngày
    public List<Map<String, Object>> getRevenueByTime(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = bookingRepository.revenueByDate(startDate, endDate);

        // Chuyển đổi kết quả từng ngày thành danh sách Map
        List<Map<String, Object>> dailyRevenueList = mapDailyRevenueResults(results);

        // Tính tổng doanh thu
        double totalRevenue = dailyRevenueList.stream()
                .mapToDouble(entry -> (Double) entry.get("totalRevenue"))
                .sum();

        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("date", "Total"); // Đặt "Total" cho ngày
        totalMap.put("totalRevenue", totalRevenue); // Tổng doanh thu
        dailyRevenueList.add(totalMap);

        return dailyRevenueList;
    }


    // Thống kê doanh thu theo rạp cụ thể
    public List<Map<String, Object>> getRevenueByTheater(Long theaterId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = bookingRepository.revenueByTheater(theaterId, startDate, endDate);
        List<Map<String, Object>> theaterRevenueList = mapTheaterOrMovieRevenueResults(results, "theaterName");

        double totalRevenue = theaterRevenueList.stream()
                .mapToDouble(entry -> (Double) entry.get("totalRevenue"))
                .sum();

        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("theaterName", "Total");
        totalMap.put("date", "");
        totalMap.put("totalRevenue", totalRevenue);
        theaterRevenueList.add(totalMap);

        return theaterRevenueList;
    }


    // Thống kê doanh thu theo phim cụ thể
    public List<Map<String, Object>> getRevenueByMovie(Long movieId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = bookingRepository.revenueByMovie(movieId, startDate, endDate);
        List<Map<String, Object>> movieRevenueList = mapTheaterOrMovieRevenueResults(results, "movieTitle");
        double totalRevenue = movieRevenueList.stream()
                .mapToDouble(entry -> (Double) entry.get("totalRevenue"))
                .sum();
        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("movieTitle", "Total");
        totalMap.put("date", "");
        totalMap.put("totalRevenue", totalRevenue);
        movieRevenueList.add(totalMap);

        return movieRevenueList;
    }


    // Chuyển kết quả doanh thu chỉ theo ngày
    private List<Map<String, Object>> mapDailyRevenueResults(List<Object[]> results) {
        List<Map<String, Object>> dailyRevenueList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", result[0]); // Ngày
            map.put("totalRevenue", result[1]); // Tổng doanh thu trong ngày
            dailyRevenueList.add(map);
        }
        return dailyRevenueList;
    }

    // Chuyển kết quả doanh thu theo rạp hoặc phim + ngày
    private List<Map<String, Object>> mapTheaterOrMovieRevenueResults(List<Object[]> results, String key) {
        List<Map<String, Object>> revenueList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put(key, result[0]); // Tên rạp hoặc tên phim
            map.put("date", result[1]); // Ngày
            map.put("totalRevenue", result[2]); // Tổng doanh thu
            revenueList.add(map);
        }
        return revenueList;
    }

    @Override
    public List<Map<String, Object>> getRevenueByMonthOrYear(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getRevenueByMonthOrYear(startDate, endDate);
    }

    @Override
    public Double getTotalRevenueByMonthOrYear(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getTotalRevenueByMonthOrYear(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getRevenueByMovie(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getRevenueByMovie(startDate, endDate);
    }

    @Override
    public Double getTotalRevenueByMovie(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getTotalRevenueByMovie(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getRevenueByTheater(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getRevenueByTheater(startDate, endDate);
    }

    @Override
    public Double getTotalRevenueByTheater(LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.getTotalRevenueByTheater(startDate, endDate);
    }
}
