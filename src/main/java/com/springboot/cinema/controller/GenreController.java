package com.springboot.cinema.controller;

import com.springboot.cinema.model.Genre;
import com.springboot.cinema.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Thêm @Service để Spring Boot quản lý lớp này như một service
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Genre> searchGenres(String name) {
        return genreRepository.findByNameContaining(name);
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre updateGenre(int id, Genre genreDetails) {
        Genre genre = genreRepository.findById(id).orElseThrow(); // Tìm kiếm genre theo ID, ném ngoại lệ nếu không tìm thấy
        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription()); // Thiết lập lại thuộc tính "describe"
        genre.setStatus(genreDetails.isStatus()); // Cập nhật trạng thái genre
        return genreRepository.save(genre); // Lưu lại genre sau khi chỉnh sửa
    }

    public void hideGenre(int id) {
        Genre genre = genreRepository.findById(id).orElseThrow(); // Tìm kiếm genre theo ID, ném ngoại lệ nếu không tìm thấy
        genre.setStatus(false); // Đặt trạng thái genre thành "ẩn" (false)
        genreRepository.save(genre); // Lưu lại trạng thái mới
    }
}
