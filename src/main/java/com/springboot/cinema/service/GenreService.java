package com.example.cinema.service;

import com.example.cinema.model.Genre;
import com.example.cinema.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    // Lấy tất cả các thể loại
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Tìm kiếm thể loại theo tên
    public List<Genre> searchGenres(String name) {
        return genreRepository.findByNameContaining(name);
    }

    // Thêm mới một thể loại
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Cập nhật thể loại theo id
    public Genre updateGenre(int id, Genre genreDetails) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found")); // Thêm ngoại lệ khi không tìm thấy genre

        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription()); // Đảm bảo sử dụng phương thức đúng
        genre.setStatus(genreDetails.isStatus());
        return genreRepository.save(genre); // Lưu thể loại đã được cập nhật
    }

    // Ẩn thể loại theo id
    public void hideGenre(int id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found")); // Thêm ngoại lệ khi không tìm thấy genre

        genre.setStatus(false); // Đặt trạng thái là "ẩn"
        genreRepository.save(genre); // Lưu lại trạng thái đã thay đổi
    }
}
