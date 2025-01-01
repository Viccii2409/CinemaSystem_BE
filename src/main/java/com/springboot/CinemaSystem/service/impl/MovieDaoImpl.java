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
	public MovieDaoImpl(GenreRepository genreRepository, MovieRepository movieRepository, FileStorageDao fileStorageService) {
		this.genreRepository = genreRepository;
		this.movieRepository = movieRepository;
		this.fileStorageService = fileStorageService;
	}


	@Override
	public Movie addMovie(Movie movie) {
		try {
			return movieRepository.save(movie);
		} catch (Exception e) {
			throw new DataProcessingException("Failed addMovie: " + e.getMessage());
		}
	}

	@Override
	public Movie updateMovie(Movie movie) {
		if(!movieRepository.existsById(movie.getId())) {
			throw new NotFoundException("Not found movie" + movie.getId());
		}
		try {
			return movieRepository.save(movie);
		} catch (Exception e) {
			throw new DataProcessingException("Failed updateMovie: " + e.getMessage());
		}
	}

// thêm và sửa phim
//@Override
//public Movie addMovie(MovieRequestDto movieRequestDto, MultipartFile imageFile, MultipartFile trailerFile) {
//
//	// Kiểm tra fileStorageService
//	if (fileStorageService == null) {
//		throw new NullPointerException("fileStorageService is not injected!");
//	}
//
//	Movie movie = new Movie();
//	movie.setTitle(movieRequestDto.getTitle());
//	movie.setDuration(movieRequestDto.getDuration());
//	movie.setReleaseDate(movieRequestDto.getReleaseDate());
//	movie.setDescription(movieRequestDto.getDescription());
//	movie.setDirector(movieRequestDto.getDirector());
//	movie.setCast(movieRequestDto.getCast());
//	movie.setLanguage(movieRequestDto.getLanguage());
//	movie.setStatus(true);
//
//	// Xử lý danh sách thể loại
//	List<Genre> genre = movieRequestDto.getGenre()
//			.stream()
//			.map(dto -> genreRepository.findById(dto.getID())
//					.orElseThrow(() -> new RuntimeException("Genre not found")))
//			.collect(Collectors.toList());
//	movie.setGenres(genre);
//
//	try {
//		// Upload ảnh lên Cloudinary vào thư mục 'Movie'
//		if (imageFile != null && !imageFile.isEmpty()) {
//			String imageUrl = fileStorageService.saveFileMovieAndTrailer(imageFile, "Movie");
//			movie.setImage(imageUrl);
//		}
//
//		// Upload trailer lên Cloudinary vào thư mục 'Trailer'
//		if (trailerFile != null && !trailerFile.isEmpty()) {
//			String trailerUrl = fileStorageService.saveFileMovieAndTrailer(trailerFile, "Trailer");
//			movie.setTrailer(trailerUrl);
//		}
//
//	} catch (Exception e) {
//		e.printStackTrace();
//		throw new RuntimeException("Failed to upload files to Cloudinary", e);
//	}
//
//	return movieRepository.save(movie);
//}

//	@Override
//	public Movie editMovie(long id, MovieRequestDto movieRequestDto, MultipartFile imageFile, MultipartFile trailerFile) {
//		// Kiểm tra sự tồn tại của phim với id
//		Optional<Movie> movieOpt = movieRepository.findById(id);
//		if (movieOpt.isPresent()) {
//			Movie movie = movieOpt.get();
//
//			// Cập nhật các thuộc tính cơ bản của phim
//			movie.setTitle(movieRequestDto.getTitle());
//			movie.setDuration(movieRequestDto.getDuration());
//			movie.setReleaseDate(movieRequestDto.getReleaseDate());
//			movie.setDescription(movieRequestDto.getDescription());
//			movie.setDirector(movieRequestDto.getDirector());
//			movie.setCast(movieRequestDto.getCast());
////			movie.setLanguage(movieRequestDto.getLanguage());
//
//			// Cập nhật thể loại
//			List<Genre> genres = movieRequestDto.getGenre()
//					.stream()
//					.map(dto -> genreRepository.findById(dto.getID())
//							.orElseThrow(() -> new RuntimeException("Genre not found")))
//					.collect(Collectors.toList());
//			movie.setGenres(genres);
//
//			try {
//				// Upload ảnh lên Cloudinary vào thư mục 'Movie' nếu có thay đổi
//				if (imageFile != null && !imageFile.isEmpty()) {
//					String imageUrl = fileStorageService.saveFileMovieAndTrailer(imageFile, "Movie");
//					movie.setImage(imageUrl);  // Cập nhật ảnh mới
//				}
//
//				// Upload trailer lên Cloudinary vào thư mục 'Trailer' nếu có thay đổi
//				if (trailerFile != null && !trailerFile.isEmpty()) {
//					String trailerUrl = fileStorageService.saveFileMovieAndTrailer(trailerFile, "Trailer");
//					movie.setTrailer(trailerUrl);  // Cập nhật trailer mới
//				}
//
//			} catch (Exception e) {
//				throw new RuntimeException("Failed to upload files to Cloudinary", e);  // Nếu có lỗi khi upload
//			}
//
//			// Lưu lại thông tin phim đã chỉnh sửa vào cơ sở dữ liệu
//			return movieRepository.save(movie);
//		} else {
//			throw new RuntimeException("Movie not found with id " + id);  // Trường hợp không tìm thấy phim với id
//		}
//	}

    public List<Genre> customerGenre(Long customerID){
        return customerRepository.findGenresByCustomerId(customerID);
    }

    public List<MovieDto> recommendMovies(List<Long> genreIds) {
        List<Movie> movies=movieRepository.findMoviesByGenres(genreIds);
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

	public List<MovieDto> getCommingSoonMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findMoviesComingSoon(today).stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public List<MovieDto> getShowingNowMovie() {
		LocalDate today = LocalDate.now();
		return movieRepository.findMoviesNowShowing(today).stream()
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

//	@Override
//	public Trailer saveOrUpdateTrailer(Trailer trailer) {
//		// Kiểm tra trailer có sẵn với movieId
//		Optional<Trailer> existingTrailer = trailerRepository.findByMovieId(trailer.getMovie().getId());
//
//		if (existingTrailer.isPresent()) {
//			// Nếu đã tồn tại trailer cho movieId, cập nhật thông tin trailer
//			Trailer currentTrailer = existingTrailer.get();
//			currentTrailer.setDescription(trailer.getDescription());
//			currentTrailer.setLink(trailer.getLink());
//			return trailerRepository.save(currentTrailer); // Cập nhật trailer
//		} else {
//			// Nếu chưa có trailer cho movieId, lưu mới
//			return trailerRepository.save(trailer);
//		}
//	}


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

	@Override
	public List<Movie> getMoviesByGenre(int genreID) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByLanguage(int languageID) {
		return List.of();
	}

	@Override
	public List<Movie> searchMoviesByTitle(String title) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByCastID(int castID) {
		return List.of();
	}

	@Override
	public List<Movie> getMoviesByDirectorID(int directorID) {
		return List.of();
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
	public boolean updateStatusGenre(long genreID) {
		return false;
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
}