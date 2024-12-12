package com.springboot.CinemaSystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.*;
import com.springboot.CinemaSystem.service.impl.LanguageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    private MovieDao movieService;
    private DiscountDao discountDao;
    private TheaterDao theaterDao;
    private SlideshowDao slideshowDao;
    private ShowtimeDao showtimeDao;
    private MovieRepository movieRepository;
    private LanguageDao languageDao;


    @Autowired
    public MovieController(MovieDao movieService, DiscountDao discountDao, TheaterDao theaterDao, SlideshowDao slideshowDao, ShowtimeDao showtimeDao, MovieRepository movieRepository, LanguageDaoImpl languageDao) {
        this.movieService = movieService;
        this.discountDao = discountDao;
        this.theaterDao = theaterDao;
        this.slideshowDao = slideshowDao;
        this.showtimeDao = showtimeDao;
        this.movieRepository = movieRepository;
        this.languageDao = languageDao;
    }

    @GetMapping("/getAll")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDetailDto getMovieById(@PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id);
        if(movie != null ){
            return movie.toMovieDetailDto();
        }
        throw new NotFoundException("Movie not found with ID: " + id);
    }

    @GetMapping("/showingNow")
    public List<MovieDto> getShowingNowMovie(){
        return movieService.getShowingNowMovie();
    }

    @GetMapping("/comingSoon")
    public List<MovieDto> getCommingSoonMovie(){
        return movieService.getCommingSoonMovie();
    }

    @GetMapping("/discount")
    public List<DiscountDto> getAllDiscounts(){
        List<DiscountDto> discountDtos = new ArrayList<>();
        List<Discount> discounts = discountDao.getAllDiscounts();
        for(Discount discount : discounts) {
            discountDtos.add(discount.toDiscountDto());
        }
        return discountDtos;
    }

    @GetMapping("/slideshow")
    public List<Slideshow> getAllSlideshow(){
        return slideshowDao.getAllSlideshow();
    }

    // Quản lý thể loại
    @PostMapping("/genre/add")
    public Genre addGenre(@RequestBody Genre genre) {
        System.out.println(genre);
        return movieService.addGenre(genre);
    }

    @GetMapping("/genre/{id}")
    public Genre getGenre(@PathVariable("id") long id) {
        System.out.println(id);
        return movieService.getGenreByID(id);
    }

    @GetMapping("/genre")
    public List<GenreDto> getAllGenres(){
        List<GenreDto> genreDtos = new ArrayList<>();
        List<Genre> genres = movieService.getAllGenres();
        for(Genre g : genres) {
            genreDtos.add(g.toGenreDto());
        }
        return genreDtos;
    }

    @GetMapping("/genre/search")
    public List<Genre> searchGenres(@RequestParam("name") String name){
        return movieService.searchGenres(name);
    }

    @PutMapping("/genre/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails){
        return movieService.updateGenre(id, genreDetails);
    }

    @PutMapping("/genre/{id}/hide")
    public ResponseEntity<String> hideGenre(@PathVariable Long id) {
        movieService.hideGenre(id);
        return ResponseEntity.ok("Chuyển trạng thái thành công");

    }

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
                             @RequestParam("image") MultipartFile imageFile,
                             @RequestParam("trailer") MultipartFile trailerFile) {
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




    @PutMapping("/update-status/{id}")
    public boolean updateStatusMovie(@PathVariable int id) {
        return movieService.updateStatusMovie(id);
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }
    @GetMapping({"/getAllMovies"})
    public List<MovieDto> getAllMovie() {
        // Call the service layer to get all movies
        return movieService.getAllMovie();
    }

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
    // tìm phim theo thể loại
    @GetMapping("/searchByGenre")
    public List<MovieDto> searchByGenre(@RequestParam("genreName") String genreName) {
        return movieService.searchMoviesByGenre(genreName);
    }

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
    public ResponseEntity<Void> updateShowtime(@PathVariable long showtimeId, @RequestBody ShowtimeRequestDto showtimeRequestDto) {
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

//    // API trả về danh sách các rạp có status = 1 (hoạt động)
//    @GetMapping("/theaters")
//    public List<TheaterDto> getActiveTheaters() {
//        return theaterDao.getActiveTheaters();  // Lấy danh sách các rạp có status = 1 từ service
//    }

}
