package com.springboot.CinemaSystem.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        // Chuyển đổi từ chuỗi sang DataSize
        factory.setMaxFileSize(DataSize.parse("20MB"));  // Giới hạn kích thước tệp
        factory.setMaxRequestSize(DataSize.parse("20MB"));  // Giới hạn kích thước yêu cầu

        return factory.createMultipartConfig();
    }
}
