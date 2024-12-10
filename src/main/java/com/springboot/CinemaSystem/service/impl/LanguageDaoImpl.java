package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.Language;
import com.springboot.CinemaSystem.repository.LanguageRepository;
import com.springboot.CinemaSystem.service.LanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageDaoImpl implements LanguageDao {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageDaoImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
