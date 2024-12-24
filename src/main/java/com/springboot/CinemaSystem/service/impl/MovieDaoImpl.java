package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.GenreRepository;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.FileStorageDao;
import com.springboot.CinemaSystem.service.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieDaoImpl implements MovieDao {

	private final GenreRepository genreRepository;
	private final MovieRepository movieRepository;
	private final FileStorageDao fileStorageService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SlideshowRepository slideshowRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	public MovieDaoImpl(GenreRepository genreRepository, MovieRepository movieRepository, FileStorageDao fileStorageService) {
		this.genreRepository = genreRepository;
		this.movieRepository = movieRepository;
		this.fileStorageService = fileStorageService;
	}

// Quản lý phim
//	@Override
//	public Movie addMovie(Movie movie) {
//		try {
//			return movieRepository.save(movie);
//		} catch (Exception e) {
//			throw new DataProcessingException("Failed addMovie: " + e.getMessage());
//		}
//	}
//
//	@Override
//	public Movie updateMovie(Movie movie) {
//		if(!movieRepository.existsById(movie.getId())) {
//			throw new NotFoundException("Not found movie" + movie.getId());
//		}
//		try {
//			return movieRepository.save(movie);
//		} catch (Exception e) {
//			throw new DataProcessingException("Failed updateMovie: " + e.getMessage());
//		}
//	}

	// Thêm phim mới
	// Phương thức addMovie mới
	public MovieDto addMovie(MovieRequestDto movieRequestDto, MultipartFile imageFile, MultipartFile trailerFile) {
		Movie movie = Movie.toMovie(movieRequestDto);

		// Kiểm tra và lưu ảnh nếu có
		if (imageFile != null && !imageFile.isEmpty()) {
			String imageUrl = fileStorageService.saveFileFromCloudinary(imageFile, "Image/Movie", "image");
			movie.setImage(imageUrl);
		}

		// Kiểm tra và lưu trailer nếu có
		if (trailerFile != null && !trailerFile.isEmpty()) {
			String videoUrl = fileStorageService.saveFileFromCloudinary(trailerFile, "Video/Movie", "video");
			movie.setTrailer(videoUrl);
		}

		Movie savedMovie = movieRepository.save(movie);
		return MovieDto.toMovieDto(savedMovie);
	}


	// Cập nhật thông tin phim
	public MovieDto updateMovie(MovieRequestDto movieRequestDto, MultipartFile imageFile, MultipartFile trailerFile) {
		// Tìm phim theo ID
		Movie movie = movieRepository.findById(movieRequestDto.getId())
				.orElseThrow(() -> new NotFoundException("Not found movie with ID: " + movieRequestDto.getId()));

		movie.setTitle(movieRequestDto.getTitle());
		movie.setDuration(movieRequestDto.getDuration());
		movie.setReleaseDate(movieRequestDto.getReleaseDate());
		movie.setDescription(movieRequestDto.getDescription());
		movie.setDirector(movieRequestDto.getDirector());
		movie.setCast(movieRequestDto.getCast());

		// Cập nhật ngôn ngữ của phim
		Language language = new Language();
		language.setId(movieRequestDto.getLanguageID());
		movie.setLanguage(language);

		// Cập nhật thể loại phim
		movie.getGenre().clear();
		for (Long genreId : movieRequestDto.getGenreID()) {
			Genre genre = genreRepository.findById(genreId)
					.orElseThrow(() -> new NotFoundException("Genre not found with ID: " + genreId));
			movie.getGenre().add(genre);
		}

		// Kiểm tra và cập nhật ảnh nếu có
		if (imageFile != null && !imageFile.isEmpty()) {
			String imageUrl = fileStorageService.updateFile(imageFile, movie.getImage(), "Image/Movie", "image");
			movie.setImage(imageUrl);
		}

		// Kiểm tra và cập nhật trailer nếu có
		if (trailerFile != null && !trailerFile.isEmpty()) {
			String videoUrl = fileStorageService.updateFile(trailerFile, movie.getTrailer(), "Video/Movie", "video");
			movie.setTrailer(videoUrl);
		}

		// Lưu và trả về phim đã cập nhật
		Movie updatedMovie = movieRepository.save(movie);
		return MovieDto.toMovieDto(updatedMovie);
	}


    public List<Genre> customerGenre(Long customerID){
        return customerRepository.findGenresByCustomerId(customerID);
    }

    public List<MovieDto> recommendMovies(List<Long> genreIds) {
        List<Movie> movies=movieRepository.findMoviesByGenres(genreIds);
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

	@Override
	public List<MovieDto> getCommingSoonMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findAll().stream()
				.filter(movie -> movie.getReleaseDate().isAfter(today))
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<MovieDto> getShowingNowMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findAll().stream()
				.filter(movie -> movie.getReleaseDate().isBefore(today) || movie.getReleaseDate().isEqual(today))
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<Slideshow> getAllSlideshow() {
		try {
			return slideshowRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Failed to retrieve slideshows: " + e.getMessage());
		}
	}



// Quản lý phim
	private MovieDto convertToDto(Movie movie) {
		// Kiểm tra danh sách image
		String link = (movie.getImage() == null || movie.getImage().isEmpty()) ? null : movie.getImage();
		List<GenreDto> genreDtos = movie.getGenre().stream()
				.map(Genre::toGenreDto)
				.collect(Collectors.toList());
		return new MovieDto(
				movie.getId(),
				movie.getTitle(),
				movie.getImage(),
				movie.getReleaseDate(),
				movie.isStatus(),
				movie.getShowtime().size(),
				genreDtos,
				movie.getShowtime()
						.stream()
						.filter(showtime -> showtime.getRoom().isStatus() == true && ("upcoming".equals(showtime.getAction()) || "running".equals(showtime.getAction())))
						.map(showtime -> new ShowtimeTheaterIDDto(
								showtime.getID(),
								showtime.getDate(),
								showtime.getStartTime(),
								showtime.getEndTime(),
								showtime.getAction(),
								showtime.getRoom().getTheater().getID(),
								showtime.getRoom().getSeat().size() - showtime.getAvailableSeats(),
								showtime.getRoom().getTypeRoom().getName()
						))
						.collect(Collectors.toList())
		);
	};

	@Override
	public boolean updateStatusMovie(int movieID) {
		Movie movie = movieRepository.findById((long) movieID).orElse(null);
		if (movie != null) {
			movie.setStatus(!movie.isStatus());
			movieRepository.save(movie);
			return true;
		}
		return false;
	}

	@Override
	public Movie getMovieByID(int movieID) {
		return movieRepository.findById((long) movieID).orElse(null);
	}

	@Override
	public Movie getMovieDetails(long movieID) {

		return movieRepository.findById((long) movieID).orElse(null);
	}

	public List<Movie> getAllMovie() {
		try {
			return movieRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}


	@Override
	public List<Movie> searchMovies(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	public Movie getMovieByID(long id) {
		return movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("khong tim thay"));
	}

	@Override
	public void deleteMovie(long id) {
		try {
			movieRepository.deleteById(id);
		}
		catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public List<MovieDto> searchMoviesByGenre(String genreName) {
		// Tìm các phim theo tên thể loại
		List<Movie> movies = movieRepository.findByGenreName(genreName);

		// Kiểm tra nếu danh sách phim rỗng
		if (movies.isEmpty()) {
			System.out.println("Không tìm thấy phim cho thể loại: " + genreName);
		} else {
			System.out.println("Đã tìm thấy " + movies.size() + " phim.");
		}

		// Chuyển đổi danh sách Movie thành MovieDto
		return movies.stream()
				.map(movie -> {
					MovieDto dto = new MovieDto();
					dto.setId(movie.getId());
					dto.setTitle(movie.getTitle());

					// Chuyển đổi các thể loại (Genre) thành GenreDto
					List<GenreDto> genreDtos = movie.getGenre().stream()
							.map(genre -> genre.toGenreDto()) // Chuyển Genre thành GenreDto
							.collect(Collectors.toList());

					dto.setGenre(genreDtos); // Set danh sách GenreDto vào MovieDto
					return dto;
				})
				.collect(Collectors.toList());
	}



	// Quản lý thể loại
	@Override
	public Genre addGenre(Genre genre) {
		return genreRepository.save(genre);
	}

	@Override
	public List<Genre> searchGenres(String name) {
		return  genreRepository.findByName(name);
	}

	@Override
	public Genre updateGenre(Long id, Genre genreDetails) {
		Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
		genre.setName(genreDetails.getName());
		genre.setDescription(genreDetails.getDescription());
		genre.setStatus(genreDetails.isStatus());
		return genreRepository.save(genre);
	}

	@Override
	public void hideGenre(Long id) {
		Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
		genre.setStatus(!genre.isStatus());
		genreRepository.save(genre);
	}



	@Override
	public Genre getGenreByID(long genreID) {
		return genreRepository.findById(genreID).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
	}


	@Override
	public List<Genre> getAllGenres() {
		return  genreRepository.findAll();
	}

	@Override
	public void deleteGenre(Long id) {
		if (!genreRepository.existsById(id)) {
			throw new NotFoundException("Không tìm thấy thể loại với ID: " + id);
		}
		genreRepository.deleteById(id);
	}

	@Override
	public Feedback addFeedback(Feedback feedback) {
		try {
			return feedbackRepository.save(feedback);
		} catch (Exception e) {
			throw new DataProcessingException("Failed to add feedback: " + e.getMessage());
		}
	}

	@Override
	public List<FeedbackDto> getFeedbackByMovie(long movieID) {
		return feedbackRepository.findByMovieId(movieID).stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	private FeedbackDto convertToDto(Feedback feedback){
		return new FeedbackDto(
				feedback.getID(),
				feedback.getText(),
				feedback.getDate(),
				feedback.getStar(),
				new BookingDto(
						feedback.getBooking().getID(),
						feedback.getBooking().getShowtime().getMovie().getId(),
						feedback.getBooking().getUser().getName(),
						feedback.getBooking().getUser().getImage()
				)
		);
	};

	@Override
	public List<Map<String, Object>> getTop3Movies() {
		LocalDateTime startDate = getStartDate();
		LocalDateTime endDate = getEndDate();

		List<Map<String, Object>> movies = bookingRepository.getTop3Movies(startDate, endDate);

		// Trả về top 3 phim
		return movies.stream().limit(3).collect(Collectors.toList());
	}

	private LocalDateTime getStartDate() {
		LocalDateTime now = LocalDateTime.now();

		return now.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	private LocalDateTime getEndDate() {
		return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
	}


}