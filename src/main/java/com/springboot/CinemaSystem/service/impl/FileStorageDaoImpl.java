package com.springboot.CinemaSystem.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.FileStorageDao;
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
public class FileStorageDaoImpl implements FileStorageDao {

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

    public String saveFileFromCloudinary(MultipartFile file, String folder) {
        try {
            String publicId = "image_" + System.currentTimeMillis();

            Map map = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
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
    public String updateFile(MultipartFile file, String image, String folder) {
        try{
            if(image != null) {
                deleteFileFromCloudinary(image, folder);
            }
            return saveFileFromCloudinary(file, folder);
        }catch (Exception e){
            throw new DataProcessingException("Không cập nhật được tệp: " + e.getMessage());
        }
    }

    @Override
    public void deleteFileFromCloudinary(String image, String folder) {
        try {
            URL url = new URL(image);
            String path = url.getPath();
            String[] segments = path.split("/");
            String filename = segments[segments.length - 1];
            String imageName = filename.substring(0, filename.lastIndexOf('.'));
            String publicId = folder + "/" + imageName;

            Map checkResult = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
            if (checkResult.containsKey("public_id")) {
                Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
            }
        } catch (Exception e) {
            throw new DataProcessingException("Không thể xóa tệp trên Cloudinary: " + e.getMessage());
        }
    }

    @Override
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
