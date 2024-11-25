package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Trailer;
import org.springframework.stereotype.Service;
import com.springboot.CinemaSystem.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrailerDaoImpl {

    @Autowired
    private TrailerRepository trailerRepository;

    // Phương thức để lưu Trailer
    public Trailer saveOrUpdateTrailer(Trailer trailer) {
        // Kiểm tra trailer có sẵn với movieId
        Optional<Trailer> existingTrailer = trailerRepository.findByMovieId(trailer.getMovie().getId());

        if (existingTrailer.isPresent()) {
            // Nếu đã tồn tại trailer cho movieId, cập nhật thông tin trailer
            Trailer currentTrailer = existingTrailer.get();
            currentTrailer.setDescription(trailer.getDescription());
            currentTrailer.setLink(trailer.getLink());
            return trailerRepository.save(currentTrailer); // Cập nhật trailer
        } else {
            // Nếu chưa có trailer cho movieId, lưu mới
            return trailerRepository.save(trailer);
        }
    }
}
