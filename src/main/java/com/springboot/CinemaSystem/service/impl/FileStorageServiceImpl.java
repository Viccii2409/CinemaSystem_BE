package com.springboot.CinemaSystem.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private Cloudinary cloudinary;

    private final String uploadDir = "uploads/";

    @Override
    public String saveFile(MultipartFile file) {
        try {
            Path directory = Paths.get(uploadDir);
            if(Files.notExists(directory)){
                Files.createDirectories(directory);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            System.currentTimeMillis(): Hàm này trả về số mili giây
//            file.getOriginalFilename(): Phương thức này trả về tên gốc của tệp mà người dùng đã tải lên (bao gồm cả phần mở rộng của tệp)

            Path filePath = directory.resolve(fileName);
//            directory: Đây là đối tượng Path đại diện cho thư mục đích nơi tệp sẽ được lưu trữ.
//            directory.resolve(fileName): Phương thức resolve() được sử dụng để kết hợp đường dẫn thư mục (directory) với tên tệp (fileName).
//            Kết quả là một đường dẫn đầy đủ để lưu trữ tệp.

            Files.write(filePath, file.getBytes());
//            file.getBytes(): Phương thức này trả về nội dung của tệp dưới dạng một mảng byte (byte[]). Đây là cách để truy cập vào nội dung thực tế của tệp.
//            Files.write(filePath, file.getBytes()): Dùng để ghi nội dung của tệp vào đường dẫn (filePath) đã xác định trước đó.

            return filePath.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
        }
    }

    public String saveFileFromCloudinary(MultipartFile file, long id) {
        try {
            String publicId = "image_" + String.valueOf(id);

            Map map = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "Image",
                            "public_id", publicId,
                            "resource_type", "image"));
            return  (String) map.get("secure_url");

        } catch (IOException e) {
            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
        } catch (Exception e) {
            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
        }
    }

    @Override
    public String updateFile(MultipartFile file, long id) {
        try{
            deleteFileFromCloudinary(id);
            return saveFileFromCloudinary(file, id);
        }catch (Exception e){
            throw new DataProcessingException("Không cập nhật được tệp: " + e.getMessage());
        }
    }

    @Override
    public void deleteFileFromCloudinary(long id) {
        try {
            String publicId = "Image/image_" + String.valueOf(id);
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
            if ("ok".equals(result.get("result"))) {
                System.out.println("Đã xóa tệp trên Cloudinary: " + publicId);
            } else {
                System.out.println("Không thể xóa tệp trên Cloudinary: " + publicId);
            }
        } catch (Exception e) {
            throw new DataProcessingException("Không thể xóa tệp trên Cloudinary: " + e.getMessage());
        }
    }
}
