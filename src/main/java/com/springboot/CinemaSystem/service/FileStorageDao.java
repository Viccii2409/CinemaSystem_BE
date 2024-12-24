package com.springboot.CinemaSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageDao {
    public String saveFileFromCloudinary(MultipartFile file, String folder, String type);
    public String updateFile(MultipartFile file, String image, String folder, String type);
    public void deleteFileFromCloudinary(String imageUrl, String folder);

}
