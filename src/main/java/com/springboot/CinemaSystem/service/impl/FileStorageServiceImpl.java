package com.springboot.CinemaSystem.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
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

    public String saveFileFromCloudinary(MultipartFile file) {
        try {
            String publicId = "image_" + System.currentTimeMillis();

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
    public String updateFile(MultipartFile file, String image) {
        try{
            deleteFileFromCloudinary(image);
            return saveFileFromCloudinary(file);
        }catch (Exception e){
            throw new DataProcessingException("Không cập nhật được tệp: " + e.getMessage());
        }
    }

    @Override
    public void deleteFileFromCloudinary(String image) {
        try {
            URL url = new URL(image);
            String path = url.getPath();
            String[] segments = path.split("/");
            String filename = segments[segments.length - 1];
            String imageName = filename.substring(0, filename.lastIndexOf('.'));
            String publicId = "Image/" + imageName;

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

    // lưu ảnh và trailer
//    public String saveFileMovieAndTrailer(MultipartFile file, String folder) {
//        try {
//            String publicId = folder + "/" + System.currentTimeMillis();
//
//            Map map = this.cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.asMap(
//                            "folder", folder,
//                            "public_id", publicId,
//                            "resource_type", "auto"));
//            return (String) map.get("secure_url");
//
//        } catch (Exception e) {
//            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
//        }
//    }
//    public String saveFileMovieAndTrailer(MultipartFile file, String folder) throws IOException {
//        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                ObjectUtils.asMap("folder", folder));
//        return uploadResult.get("url"); // Trả về URL của file đã upload
//    }
//    public String saveFileMovieAndTrailer(MultipartFile file, String folder) throws IOException {
//        if (file == null || file.isEmpty()) {
//            throw new RuntimeException("File is empty");
//        }
//
//        // Kiểm tra Content-Type là image/jpeg hoặc image/png
//        String contentType = file.getContentType();
//        if (!contentType.startsWith("image/")) {
//            throw new RuntimeException("Invalid image file type. Expected JPG or PNG.");
//        }
//
//        // Kiểm tra định dạng file (JPG, PNG)
//        String fileName = file.getOriginalFilename();
//        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
//
//        if (!fileExtension.equals("jpg") && !fileExtension.equals("png")) {
//            throw new RuntimeException("Invalid image file type. Expected JPG or PNG.");
//        }
//
//        try {
//            Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.asMap("folder", folder));
//            return uploadResult.get("secure_url"); // Trả về URL của file đã upload
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to upload image to Cloudinary", e);
//        }
//    }
    public String saveFileMovieAndTrailer(MultipartFile file, String folder) throws IOException {
        // Chuyển MultipartFile thành mảng byte
        byte[] fileBytes = file.getBytes();

        // Xác định loại tài nguyên (ảnh, video)
        String resourceType = file.getContentType().startsWith("image") ? "image" : "video";

        // Upload tệp lên Cloudinary
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.asMap(
                "folder", folder,           // Chỉ định thư mục trên Cloudinary
                "resource_type", resourceType)); // Xác định loại tài nguyên (image hoặc video)

        // Trả về URL của tệp đã upload
        return (String) uploadResult.get("url");
    }


}
