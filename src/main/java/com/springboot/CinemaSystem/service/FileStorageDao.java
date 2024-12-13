package com.springboot.CinemaSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageDao {
    public String saveFile(MultipartFile file);
    public String saveFileFromCloudinary(MultipartFile file, String folder);

    public String updateFile(MultipartFile file, String image, String folder);
    public void deleteFileFromCloudinary(String image, String folder);
    public String saveFileMovieAndTrailer(MultipartFile file, String folder) throws IOException;

}
