package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Language;
import com.springboot.CinemaSystem.entity.Movie;

import java.util.List;

public interface LanguageDao {
    public List<Language> getAllLanguages();

}
