package com.springboot.CinemaSystem.controller;


import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.mapper.FeedbackMapper;
import com.springboot.CinemaSystem.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private MovieDao movieService;
    private ShowtimeDao showtimeDao;
    @Autowired
    private FileStorageDao fileStorageDao;
    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private RevenueDao revenueDao;


    @Autowired
    public MovieController(MovieDao movieService, ShowtimeDao showtimeDao,RevenueDao revenueDao) {
        this.movieService = movieService;
        this.showtimeDao = showtimeDao;
        this.revenueDao = revenueDao;
    }



    @GetMapping("/public/{id}")
    public MovieDetailAdminDto getMovieById(@PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id);
        if(movie != null ){
            return movie.toMovieDetailAdminDto();
        }
        throw new NotFoundException("Movie not found with ID: " + id);
    }

    @GetMapping("/public/showingNow")
    public List<MovieDto> getShowingNowMovie(){
        return movieService.getShowingNowMovie();
    }

    @GetMapping("/public/comingSoon")
    public List<MovieDto> getCommingSoonMovie(){
        return movieService.getCommingSoonMovie();
    }

    @GetMapping("/public/slideshow")
    public List<Slideshow> getAllSlideshow(){
        return movieService.getAllSlideshow();
    }

    @GetMapping("/public/all")
    public List<MovieDto> getAllMoviePublic() {
        return movieService.getAllMovie().stream()
                .filter(entry -> entry.isStatus())
                .map(entry -> entry.toMovieDto())
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('MANAGER_PRICETICKET')")
    @GetMapping("/baseprice")
    public BasePrice getBasePrice() {
        return showtimeDao.getBasePrice();
    }

    @PreAuthorize("hasAuthority('MANAGER_PRICETICKET')")
    @GetMapping("/dayofweek")
    public List<DayOfWeek> getDayOfWeek() {
        return showtimeDao.getAllDayOfWeeks();
    }

    @PreAuthorize("hasAuthority('MANAGER_PRICETICKET')")
    @GetMapping("/timeframe")
    public List<TimeFrame> getTimeFrame() {
        return showtimeDao.getAllTimeFrames();
    }

    // Quản lý thể loại
    @GetMapping("/public/genre")
    public List<GenreDto> getAllGenres(){
        List<GenreDto> genreDtos = movieService.getAllGenres().stream()
                .map(entry -> entry.toGenreDto())
                .sorted(Comparator.comparing(GenreDto::getID).reversed())
                .collect(Collectors.toList());
        return genreDtos;
    }

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @GetMapping("/genre/search")
    public List<Genre> searchGenres(@RequestParam("name") String name){
        return movieService.searchGenres(name);
    }

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @PostMapping("/genre/add")
    public Genre addGenre(@RequestBody Genre genre) {
        return movieService.addGenre(genre);
    }

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @GetMapping("/genre/{id}")
    public Genre getGenre(@PathVariable("id") long id) {
        System.out.println(id);
        return movieService.getGenreByID(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @PutMapping("/genre/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails){
        return movieService.updateGenre(id, genreDetails);
    }

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @DeleteMapping("/genre/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) {
        Genre genre = movieService.getGenreByID(id);
        if (genre != null) {
            movieService.deleteGenre(id);
            return ResponseEntity.ok("Xóa thể loại thành công");
        } else {
            throw new NotFoundException("Genre not found with ID: " + id);
        }
    }
    // Quản lý phim
    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/moviedetails/{id}")
    public MovieDetailAdminDto getMovieDetails(@PathVariable("id") long id){
        Movie movie = movieService.getMovieDetails(id);
        if(movie != null ){
            return movie.toMovieDetailAdminDto();
        }
        throw new NotFoundException("Movie not found with ID: " + id);
    }

    @PostMapping("/add")
    @Transactional
    public MovieDto addMovie(@ModelAttribute MovieRequestDto movieRequestDto,
                             @RequestParam(value = "image", required = false) MultipartFile imageFile,
                             @RequestParam(value = "trailer", required = false) MultipartFile trailerFile) {
        return movieService.addMovie(movieRequestDto, imageFile, trailerFile);
    }

        @PostMapping("/update")
        @Transactional
        public MovieDto updateMovie(@ModelAttribute MovieRequestDto movieRequestDto,
                                    @RequestParam(value = "image", required = false) MultipartFile imageFile,
                                    @RequestParam(value = "trailer", required = false) MultipartFile trailerFile) {
            return movieService.updateMovie(movieRequestDto, imageFile, trailerFile);
        }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @DeleteMapping("/{id}")
    public boolean deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieService.getMovieByID(id);
        if (movie != null) {
            fileStorageDao.deleteFileFromCloudinary(movie.getImage(), "Image/Movie");
            fileStorageDao.deleteFileFromCloudinary(movie.getTrailer(), "Video/Movie");
            movieService.deleteMovie(id);
            return true;
        } else {
            throw new NotFoundException("Movie not found with ID: " + id);
        }
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @PutMapping("/update-status/{id}")
    public boolean updateStatusMovie(@PathVariable int id) {
        return movieService.updateStatusMovie(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping({"/all"})
    public List<MovieDto> getAllMovie() {
        return movieService.getAllMovie().stream()
                .map(entry -> MovieDto.toMovieDto(entry))
                .sorted(Comparator.comparing(MovieDto::getId).reversed())
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/searchByGenre")
    public List<MovieDto> searchByGenre(@RequestParam("genreName") String genreName) {
        return movieService.searchMoviesByGenre(genreName);
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/allMovie")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovie();
    }

    // API lấy danh sách ngôn ngữ
    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/getAllLanguage")
    public List<Language> getAllLanguages() {
        return movieService.getAllLanguages();
    }


    ///  LÊN LỊCH CHIẾU
    ///
// Lấy danh sách phòng chiếu và lịch chiếu theo ngày và rạp
    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @GetMapping("/showtimes")
    public ResponseEntity<List<RoomShowtimeDto>> getShowtimesByDateAndTheater(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("theaterId") long theaterId) {

        List<RoomShowtimeDto> rooms = showtimeDao.getRoomsByTheater(theaterId);

        // Gán danh sách lịch chiếu vào từng phòng chiếu cho ngày cụ thể
        for (RoomShowtimeDto room : rooms) {
            List<ShowtimeDto> showtimes = showtimeDao.getShowtimesByDateAndRoom(date, room.getRoomId());
            room.setShowtimes(showtimes);
        }

        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @PostMapping("/schedule")
    public ResponseEntity<Showtime> scheduleShowtime(@RequestBody ShowtimeRequestDto dto) {
        Showtime showtime = showtimeDao.scheduleShowtime(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(showtime);
    }

    // Ẩn hoặc mở lịch chiếu
    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<Void> toggleShowtimeStatus(@PathVariable long id) {
        showtimeDao.toggleShowtimeStatus(id);
        return ResponseEntity.ok().build();
    }

    // Sửa lịch chiếu
    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @PutMapping("/showtime/update/{showtimeId}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable long showtimeId, @RequestBody ShowtimeRequestDto showtimeRequestDto) {
        showtimeDao.updateShowtime(showtimeId, showtimeRequestDto);
        return ResponseEntity.ok().build();
    }

    // Xóa lịch chiếu
    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @DeleteMapping("/showtime/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable long id) {
        showtimeDao.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }

    // API để lấy chi tiết lịch chiếu
    @PreAuthorize("hasAuthority('MANAGER_SHOWTIME')")
    @GetMapping("/showtime/{id}")
    public ResponseEntity<ShowtimeDetailDto> getShowtimeDetail(@PathVariable long id) {
        ShowtimeDetailDto showtimeDetail = showtimeDao.getShowtimeDetailById(id);
        if (showtimeDetail != null) {
            return ResponseEntity.ok(showtimeDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PostMapping("/add-feedback")
    public FeedbackDto addFeedback(@RequestBody FeedbackAddDto feedbackAddDto){
        try {
            if (feedbackAddDto == null || feedbackAddDto.getText() == null || feedbackAddDto.getStar() == null) {
                throw new IllegalArgumentException("Thiếu thông tin cần thiết cho Feedback");
            }

            // Lấy Booking từ cơ sở dữ liệu
            Booking booking = ticketDao.getBookingById(feedbackAddDto.getBookingId());
            if(booking.getFeedback() != null) {
                throw new DataProcessingException("Feedback đã tồn tại cho booking này");
            }
            Feedback feedback = FeedbackMapper.toFeedbackAdd(feedbackAddDto, booking);
            Feedback saveFeedback = movieService.addFeedback(feedback);
            return FeedbackMapper.toFeedbackDto(saveFeedback);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm feedback: " + e.getMessage());
        }
    }


    @GetMapping("/public/feedback/{movieId}")
    public List<FeedbackDto> getFeedbackByMovie(@PathVariable long movieId) {
        return movieService.getFeedbackByMovie(movieId);
    }

    // Thống kê doanh thu theo phim và thời gian
    @PreAuthorize("hasAuthority('MANAGER_REVENUE')")
    @GetMapping("/movie-revenue")
    public ResponseEntity<?> getRevenueByMovie(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(revenueDao.getRevenueByMovie(movieId, startDate, endDate));
    }

    // Thống kê doanh thu tất cả phim +  có thể chọn tgian hoặc không
    @PreAuthorize("hasAuthority('MANAGER_REVENUE')")
    @GetMapping("/by-movie")
    public ResponseEntity<?> getRevenueByMovie(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        if (startDate == null) startDate = LocalDateTime.of(2000, 1, 1, 0, 0);
        if (endDate == null) endDate = LocalDateTime.now();

        List<Map<String, Object>> revenue = revenueDao.getRevenueByMovie(startDate, endDate);
        Double totalRevenue = revenueDao.getTotalRevenueByMovie(startDate, endDate);

        return ResponseEntity.ok(Map.of("details", revenue, "totalRevenue", totalRevenue));
    }

    // Top 3 phim

    @GetMapping("/public/topMovies")
    public List<Map<String, Object>> getTop3Movies() {
        return movieService.getTop3Movies();
    }
}