package com.springboot.CinemaSystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    public String saveFile(MultipartFile file);
//    public String saveFileMovieAndTrailer(MultipartFile file,String folder);
    public String saveFileMovieAndTrailer(MultipartFile file, String folder) throws IOException;

    public String saveFileFromCloudinary(MultipartFile file);
    public String updateFile(MultipartFile file, String image);
    public void deleteFileFromCloudinary(String image);

}
