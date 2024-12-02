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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieDao movieService;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    private TheaterDao theaterDao;
    @Autowired
    private SlideshowDao slideshowDao;
    @Autowired
    private ShowtimeDao showtimeDao;
    private MovieRepository movieRepository;

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
    public boolean addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }


    @PutMapping("/{ID}")
    public boolean editMovie(@PathVariable Long ID, @RequestBody Movie movie) {
        return movieService.editMovie(ID, movie);
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

// update trailer cho phim
    @Autowired
    private TrailerDaoImpl trailerDao;

    // Thêm trailer mới hoặc cập nhật trailer nếu movieId đã tồn tại
    @PostMapping("/addTrailer")
    public String addTrailer(@RequestBody Trailer trailer) {
        // Lưu trailer, nếu trùng movieId thì sẽ cập nhật, không thì sẽ lưu mới
        trailerDao.saveOrUpdateTrailer(trailer);
        return "redirect:/movies"; // Quay lại trang danh sách movies
    }


    
    ///  LÊN LỊCH CHIẾU     < chưa có hiển thị danh sách lịch chiếu khi chọn ngày + rạp>
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
