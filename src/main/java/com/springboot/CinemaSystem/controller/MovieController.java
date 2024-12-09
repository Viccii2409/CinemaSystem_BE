package com.springboot.CinemaSystem.controller;


import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.Movie;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.service.*;
import com.springboot.CinemaSystem.service.impl.TrailerDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private TrailerDaoImpl trailerDao;


    @Autowired
    public MovieController(MovieDao movieService, DiscountDao discountDao, TheaterDao theaterDao, SlideshowDao slideshowDao, ShowtimeDao showtimeDao, MovieRepository movieRepository, TrailerDaoImpl trailerDao) {
        this.movieService = movieService;
        this.discountDao = discountDao;
        this.theaterDao = theaterDao;
        this.slideshowDao = slideshowDao;
        this.showtimeDao = showtimeDao;
        this.movieRepository = movieRepository;
        this.trailerDao = trailerDao;
    }

    @GetMapping("/public/{id}")
    public MovieDetailDto getMovieById(@PathVariable("id") long id){
        Movie movie = movieService.getMovieByID(id);
        if(movie != null ){
            return movie.toMovieDetailDto();
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
        return slideshowDao.getAllSlideshow();
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
    @PostMapping("/add")
    public boolean addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PreAuthorize("hasAuthority('MANAGER_MOVIE')")
    @PutMapping("/{ID}")
    public boolean editMovie(@PathVariable Long ID, @RequestBody Movie movie) {
        return movieService.editMovie(ID, movie);
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

//    @GetMapping("/public/discount")
//    public List<DiscountDto> getAllDiscounts(){
//        List<DiscountDto> discountDtos = new ArrayList<>();
//        List<Discount> discounts = discountDao.getAllDiscounts();
//        for(Discount discount : discounts) {
//            discountDtos.add(discount.toDiscountDto());
//        }
//        return discountDtos;
//    }



    // Thêm trailer mới hoặc cập nhật trailer nếu movieId đã tồn tại
    @PostMapping("/addTrailer")
    public String addTrailer(@RequestBody Trailer trailer) {
        // Lưu trailer, nếu trùng movieId thì sẽ cập nhật, không thì sẽ lưu mới
        trailerDao.saveOrUpdateTrailer(trailer);
        return "redirect:/movies"; // Quay lại trang danh sách movies
    }






    ///  LÊN LỊCH CHIẾU
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