package com.springboot.CinemaSystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.*;
import com.springboot.CinemaSystem.service.impl.LanguageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private MovieDao movieService;
    private ShowtimeDao showtimeDao;
    @Autowired
    private LanguageDao languageDao;


    @Autowired
    public MovieController(MovieDao movieService, ShowtimeDao showtimeDao) {
        this.movieService = movieService;
        this.showtimeDao = showtimeDao;
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

    @GetMapping("/public/genre")
    public List<GenreDto> getAllGenre() {
        return movieService.getAllGenres().stream()
                .filter(entry -> entry.isStatus())
                .map(entry -> GenreDto.toGenreDto(entry))
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

    @PreAuthorize("hasAuthority('MANAGER_GENRE')")
    @GetMapping("/genre")
    public List<GenreDto> getAllGenres(){
        List<GenreDto> genreDtos = new ArrayList<>();
        List<Genre> genres = movieService.getAllGenres();
        for(Genre g : genres) {
            genreDtos.add(g.toGenreDto());
        }
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
        System.out.println(genre);
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
    @PutMapping("/genre/{id}/hide")
    public ResponseEntity<String> hideGenre(@PathVariable Long id) {
        movieService.hideGenre(id);
        return ResponseEntity.ok("Chuyển trạng thái thành công");

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
    public boolean addMovie(@RequestParam("movie") String movieRequestDtoJson,
                            @RequestParam("image") MultipartFile imageFile,
                            @RequestParam("trailer") MultipartFile trailerFile) {
        try {
            // Tạo ObjectMapper và đăng ký JavaTimeModule
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Chuyển đổi JSON movieRequestDto thành đối tượng MovieRequestDto
            MovieRequestDto movieRequestDto = new ObjectMapper().readValue(movieRequestDtoJson, MovieRequestDto.class);

            // Gọi service để thêm movie
            return movieService.addMovie(movieRequestDto, imageFile, trailerFile) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping("/{ID}")
    public boolean editMovie(@PathVariable long ID,
                             @RequestParam("movie") String movieRequestDtoJson,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                             @RequestParam(value = "trailerFile", required = false) MultipartFile trailerFile) {
        try {
            // Chuyển đổi JSON movieRequestDto thành đối tượng MovieRequestDto
            MovieRequestDto movieRequestDto = new ObjectMapper().readValue(movieRequestDtoJson, MovieRequestDto.class);

            // Gọi service để sửa movie
            return movieService.editMovie(ID, movieRequestDto, imageFile, trailerFile) != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        // Call the service layer to get all movies
        return movieService.getAllMovie();
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieService.getMovieByID(id);
        if (movie != null) {
            movieService.deleteMovie(id);
            return ResponseEntity.ok("Xóa phim thành công");
        } else {
            throw new NotFoundException("Movie not found with ID: " + id);
        }
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @GetMapping("/searchByGenre")
    public List<MovieDto> searchByGenre(@RequestParam("genreName") String genreName) {
        return movieService.searchMoviesByGenre(genreName);
    }

    @GetMapping("/allMovie")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    // Thêm trailer mới hoặc cập nhật trailer nếu movieId đã tồn tại
//    @PostMapping("/addTrailer")
//    public String addTrailer(@RequestBody Trailer trailer) {
//        // Lưu trailer, nếu trùng movieId thì sẽ cập nhật, không thì sẽ lưu mới
//        movieService.saveOrUpdateTrailer(trailer);
//        return "redirect:/movies"; // Quay lại trang danh sách movies
//    }
    // API lấy danh sách ngôn ngữ
    @GetMapping("/getAllLanguage")
    public List<Language> getAllLanguages() {
        return languageDao.getAllLanguages();
    }


    ///  LÊN LỊCH CHIẾU     < chưa có hiển thị danh sách lịch chiếu khi chọn ngày + rạp>
// Lấy danh sách phòng chiếu và lịch chiếu theo ngày và rạp
    @GetMapping("/showtimes")
    public ResponseEntity<List<RoomShowtimeDto>> getShowtimesByDateAndTheater(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("theaterId") long theaterId) {

        // Lấy danh sách phòng và lịch chiếu theo ngày và rạp
        List<RoomShowtimeDto> rooms = showtimeDao.getRoomsByTheater(theaterId);

        // Gán danh sách lịch chiếu vào từng phòng chiếu cho ngày cụ thể
        for (RoomShowtimeDto room : rooms) {
            List<ShowtimeDto> showtimes = showtimeDao.getShowtimesByDateAndRoom(date, room.getRoomId());
            room.setShowtimes(showtimes);  // Gán danh sách lịch chiếu
        }

        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Showtime> scheduleShowtime(@RequestBody ShowtimeRequestDto dto) {
        Showtime showtime = showtimeDao.scheduleShowtime(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(showtime);
    }
    // Cập nhật trạng thái tự động
    @PutMapping("/status/update")
    public ResponseEntity<Void> updateShowtimeStatus() {
        showtimeDao.updateShowtimeStatus();
        return ResponseEntity.ok().build();
    }

    // Ẩn hoặc mở lịch chiếu
    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<Void> toggleShowtimeStatus(@PathVariable long id) {
        showtimeDao.toggleShowtimeStatus(id);
        return ResponseEntity.ok().build();
    }

    // Sửa lịch chiếu
    @PutMapping("/showtime/update/{showtimeId}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable long showtimeId, @RequestBody ShowtimeRequestDto showtimeRequestDto) {
        showtimeDao.updateShowtime(showtimeId, showtimeRequestDto);
        return ResponseEntity.ok().build();
    }

    // Xóa lịch chiếu
    @DeleteMapping("/showtime/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable long id) {
        showtimeDao.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }
    // Ẩn lịch chiếu khi phim ngừng chiếu
    @PutMapping("/movie/{movieId}/hide-showtimes")
    public ResponseEntity<Void> hideShowtimesByMovie(@PathVariable long movieId) {
        showtimeDao.hideShowtimesByMovie(movieId);
        return ResponseEntity.ok().build();
    }
    // API để lấy chi tiết lịch chiếu
    @GetMapping("/showtime/{id}")
    public ResponseEntity<ShowtimeDetailDto> getShowtimeDetail(@PathVariable long id) {
        ShowtimeDetailDto showtimeDetail = showtimeDao.getShowtimeDetailById(id);
        if (showtimeDetail != null) {
            return ResponseEntity.ok(showtimeDetail);
        } else {
            return ResponseEntity.notFound().build();  // Nếu không tìm thấy lịch chiếu
        }
    }


}