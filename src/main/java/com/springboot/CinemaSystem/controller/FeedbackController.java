package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.FeedbackAddDto;
import com.springboot.CinemaSystem.dto.FeedbackDto;
import com.springboot.CinemaSystem.entity.Booking;
import com.springboot.CinemaSystem.entity.Feedback;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.mapper.FeedbackMapper;
import com.springboot.CinemaSystem.repository.BookingRepository;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.FeedbackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackDao feedbackDao;
@Autowired
private BookingRepository bookingRepository;
@Autowired
private MovieRepository movieRepository;
@PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PostMapping("/add-feedback")
    public FeedbackDto addFeedback(@RequestBody FeedbackAddDto feedbackAddDto){
        try {
            if (feedbackAddDto == null || feedbackAddDto.getText() == null || feedbackAddDto.getStar() == null) {
                throw new IllegalArgumentException("Thiếu thông tin cần thiết cho Feedback");
            }

            // Lấy Booking từ cơ sở dữ liệu
            Booking booking = bookingRepository.findById(feedbackAddDto.getBookingId()).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
            // Kiểm tra xem đã có Feedback cho booking này chưa
            if (feedbackDao.existsByBookingIdAndMovieId(feedbackAddDto.getBookingId())) {
                throw new IllegalArgumentException("Feedback đã tồn tại cho booking này");
            }
            // Chuyển đổi FeedbackAddDto thành Feedback entity
            Feedback feedback = FeedbackMapper.toFeedbackAdd(feedbackAddDto, booking);
            Feedback saveFeedback = feedbackDao.addFeedback(feedback);
            return FeedbackMapper.toFeedbackDto(saveFeedback);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm feedback: " + e.getMessage());
        }
    }
    @GetMapping("/public/{movieId}")
    public List<FeedbackDto> getFeedbackByMovie(@PathVariable long movieId) {
        return feedbackDao.getFeedbackByMovie(movieId);
    }
}
