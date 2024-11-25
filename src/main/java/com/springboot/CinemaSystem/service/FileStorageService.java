package com.springboot.CinemaSystem.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String saveFile(MultipartFile file);
    public String saveFileFromCloudinary(MultipartFile file);

    public String updateFile(MultipartFile file, String image);
    public void deleteFileFromCloudinary(String image);

}
