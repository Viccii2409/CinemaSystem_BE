package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.GenreDto;
import com.springboot.CinemaSystem.dto.MovieDto;
import com.springboot.CinemaSystem.dto.ShowtimeTheaterIDDto;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.GenreRepository;
import com.springboot.CinemaSystem.repository.ImageRepository;
import com.springboot.CinemaSystem.repository.MovieRepository;
import com.springboot.CinemaSystem.repository.TrailerRepository;
import com.springboot.CinemaSystem.service.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieDaoImpl implements MovieDao {

	private GenreRepository genreRepository;
	private MovieRepository movieRepository;
	private TrailerRepository trailerRepository;
	private ImageRepository imageRepository;

	@Autowired
	public MovieDaoImpl(GenreRepository genreRepository, MovieRepository movieRepository, TrailerRepository trailerRepository, ImageRepository imageRepository) {
		this.genreRepository = genreRepository;
		this.movieRepository = movieRepository;
		this.trailerRepository = trailerRepository;
		this.imageRepository = imageRepository;
	}


	@Override
	public boolean addMovie(Movie movie) {
		movieRepository.save(movie);
		return true;
	}


	@Override
	public boolean editMovie(Long ID, Movie movie) {
		if (movieRepository.existsById(ID)) {
			// Lấy movie hiện tại từ database
			Movie existingMovie = movieRepository.findById(ID).orElseThrow(() -> new RuntimeException("Movie not found"));

			// Cập nhật các trường của movie nếu cần
			existingMovie.setTitle(movie.getTitle());
			existingMovie.setDuration(movie.getDuration());
			existingMovie.setReleaseDate(movie.getReleaseDate());
			existingMovie.setDescription(movie.getDescription());
			existingMovie.setStatus(movie.isStatus());
			existingMovie.setLanguage(movie.getLanguage());
			existingMovie.setRating(movie.getRating());
			existingMovie.setDirector(movie.getDirector());

			// Cập nhật danh sách cast (nếu có sự thay đổi)
			if (movie.getCast() != null) {
				existingMovie.setCast(movie.getCast());
			}

			// Cập nhật danh sách genre (nếu có sự thay đổi)
			if (movie.getGenre() != null) {
				existingMovie.setGenre(movie.getGenre());
			}


			// Cập nhật trailer (nếu có sự thay đổi)
			Trailer trailer = movie.getTrailer();
			if (trailer != null) {
				// Kiểm tra trailer đã tồn tại chưa, nếu có thì cập nhật
				Optional<Trailer> existingTrailer = trailerRepository.findByMovieId(existingMovie.getId());
				if (existingTrailer.isPresent()) {
					// Cập nhật trailer nếu đã tồn tại
					Trailer currentTrailer = existingTrailer.get();
					currentTrailer.setDescription(trailer.getDescription());
					currentTrailer.setLink(trailer.getLink());
					trailerRepository.save(currentTrailer);  // Cập nhật trailer
				} else {
					// Nếu trailer chưa có, lưu mới
					trailer.setMovie(existingMovie);  // Đảm bảo liên kết 2 chiều
					trailerRepository.save(trailer);  // Lưu trailer mới
				}
			}

			if (movie.getImage() != null) {
				existingMovie.getImage().clear();
				movie.getImage().forEach(image -> image.setMovie(existingMovie));  // Gán movie cho ảnh
				imageRepository.saveAll(movie.getImage());
			}

			// Lưu movie đã cập nhật
			movieRepository.save(existingMovie);
			return true;
		}
		return false;  // Trường hợp không tìm thấy movie với ID
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


	private MovieDto convertToDto(Movie movie) {
		// Kiểm tra danh sách image
		String link = (movie.getImage() == null || movie.getImage().isEmpty()) ? null : movie.getImage().get(0).getLink();
		List<GenreDto> genreDtos = movie.getGenre().stream()
				.map(Genre::toGenreDto)
				.collect(Collectors.toList());
		return new MovieDto(
				movie.getId(),
				movie.getTitle(),
				link,
				movie.getReleaseDate(),
				movie.isStatus(),
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
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public List<MovieDto> getAllMovie() {
		// Get all movie entities from the repository
		List<Movie> movies = movieRepository.findAll();
		// Convert each Movie to MovieDto and return the list
		return movies.stream()
				.map(this::convertToDto)  // Convert each Movie entity to MovieDto
				.collect(Collectors.toList());
	}

//	private MovieDto convertToMovieDto(Movie movie) {
//		List<GenreDto> genreDtos = movie.getGenre().stream()
//				.map(genre -> new GenreDto(genre.getID(), genre.getName()))
//				.collect(Collectors.toList());
//
//		return new MovieDto(movie.getId(), movie.getTitle(), movie.getReleaseDate(),
//				movie.isStatus(), movie.getImage().toString(), genreDtos);
//	}

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
		movieRepository.deleteById(id);
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

					dto.setGenres(genreDtos); // Set danh sách GenreDto vào MovieDto
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
	public boolean deleteGenreMovie(int movieID, int genreID) {
		return false;
	}

	@Override
	public boolean addLanguage(Language language) {
		return false;
	}

	@Override
	public boolean updateLanguage(Language language) {
		return false;
	}

	@Override
	public Language getLanguageByID(int languageID) {
		return null;
	}

	@Override
	public List<Language> getAllLanguages() {
		return List.of();
	}

	@Override
	public boolean addTrailer(Trailer trailer) {
		return false;
	}

	@Override
	public boolean updateTrailer(Trailer trailer) {
		return false;
	}

	@Override
	public Trailer getTrailerByID(int trailerID) {
		return null;
	}

	@Override
	public Image getImage(int imageID) {
		return null;
	}

	@Override
	public boolean addImage(Image image) {
		return false;
	}

	@Override
	public boolean updateImage(Image image) {
		return false;
	}

	@Override
	public float getMovieStat(Date startDate, Date endDate) {
		return 0;
	}

}