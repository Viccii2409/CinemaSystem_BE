package com.springboot.CinemaSystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.CinemaSystem.dto.MovieRequestDto;
import org.springframework.context.annotation.Bean;

public class JacksonConfig {
    public static ObjectMapper configureMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Đăng ký JavaTimeModule để hỗ trợ Java 8 Date/Time API
        objectMapper.registerModule(new JavaTimeModule());
        // Tắt serialization dưới dạng timestamp
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    // Phương thức kiểm tra (không nên trong phần config)
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = "{\"releaseDate\": \"2024-12-09\"}";
        try {
            MovieRequestDto dto = objectMapper.readValue(json, MovieRequestDto.class);
            System.out.println(dto.getReleaseDate());
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi
        }
    }

}
