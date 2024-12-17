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
import java.util.Map;

@Service
public class FileStorageDaoImpl implements FileStorageDao {

    @Autowired
    private Cloudinary cloudinary;

    private final String uploadDir = "uploads/";

    public String saveFileFromCloudinary(MultipartFile file, String folder, String type) {
        try {
            String publicId = "image_" + System.currentTimeMillis();

            Map map = this.cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "public_id", publicId,
                            "resource_type", type));
            return  (String) map.get("secure_url");

        } catch (IOException e) {
            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
        } catch (Exception e) {
            throw new DataProcessingException("Không lưu được tệp: " + e.getMessage());
        }
    }

    @Override
    public String updateFile(MultipartFile file, String image, String folder, String type) {
        try{
            if(image != null) {
                deleteFileFromCloudinary(image, folder);
            }
            return saveFileFromCloudinary(file, folder, type);
        }catch (Exception e){
            throw new DataProcessingException("Không cập nhật được tệp: " + e.getMessage());
        }
    }

//    @Override
//    public void deleteFileFromCloudinary(String image, String folder) {
//        try {
//            URL url = new URL(image);
//            String path = url.getPath();
//            String[] segments = path.split("/");
//            String filename = segments[segments.length - 1];
//            String imageName = filename.substring(0, filename.lastIndexOf('.'));
//            String publicId = folder + "/" + imageName;
//
//            Map checkResult = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
//            if (checkResult.containsKey("public_id")) {
//                cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true));
//            }
//        } catch (Exception e) {
//            throw new DataProcessingException("Không thể xóa tệp trên Cloudinary: " + e.getMessage());
//        }
//    }

    @Override
    public void deleteFileFromCloudinary(String image, String folder) {
        try {
            if (image != null) {
                URL url = new URL(image);
                String path = url.getPath();
                String[] segments = path.split("/");
                String filename = segments[segments.length - 1];
                String imageName = filename.substring(0, filename.lastIndexOf('.'));
                String publicId = folder + "/" + imageName;

                // Kiểm tra loại tài nguyên (image hay video)
                String resourceType = image.contains("video") ? "video" : "image";

                Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true, "resource_type", resourceType));
                System.out.println("Kết quả xóa từ Cloudinary: " + result);
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
