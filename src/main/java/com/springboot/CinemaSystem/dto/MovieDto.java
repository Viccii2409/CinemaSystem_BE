package com.springboot.CinemaSystem.dto;

import com.springboot.CinemaSystem.entity.Movie;
import lombok.*;
import com.springboot.CinemaSystem.entity.Genre;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String link;
    private LocalDate releaseDate;
    private boolean status;
    private List<GenreDto> genres;

    public MovieDto(long id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public MovieDto(Long id, String title, LocalDate releaseDate, boolean status, List<GenreDto> genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.status = status;
        this.genres = genres;
    }
    public MovieDto toMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());

        // Chuyển đổi các thể loại của phim thành danh sách GenreDto
        List<GenreDto> genreDtos = movie.getGenre().stream()
                .map(Genre::toGenreDto)
                .collect(Collectors.toList());
        dto.setGenres(genreDtos);

        return dto;
    }

}