package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.Slideshow;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.repository.SlideshowRepository;
import com.springboot.CinemaSystem.service.SlideshowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideshowDaoImpl implements SlideshowDao {
    @Autowired
    private SlideshowRepository slideshowRepository;
    @Override
    public List<Slideshow> getAllSlideshow() {
        try {
            return slideshowRepository.findAll();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve slideshows: " + e.getMessage());
        }
    }
}
