package com.springboot.CinemaSystem.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String saveFile(MultipartFile file);
    public String saveFileFromCloudinary(MultipartFile file, long id);

    public String updateFile(MultipartFile file, long id);
    public void deleteFileFromCloudinary(long id);

}