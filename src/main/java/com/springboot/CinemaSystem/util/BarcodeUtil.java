package com.springboot.CinemaSystem.util;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class BarcodeUtil {
//    public static byte[] generateBarcodeImage(String barcodeText) throws Exception {
//        // Cấu hình các tham số của barcode
//        Map<EncodeHintType, Object> hintMap = new HashMap<>();
//        hintMap.put(EncodeHintType.MARGIN, 1);
//
//        // Tạo mã barcode
//        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, 300, 150, hintMap);
//
//        // Chuyển đổi bitMatrix thành BufferedImage
//        BufferedImage image = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < 300; x++) {
//            for (int y = 0; y < 150; y++) {
//                image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
//            }
//        }
//
//        // Chuyển hình ảnh thành byte array
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", byteArrayOutputStream);
//
//        return byteArrayOutputStream.toByteArray();
//    }


    public static byte[] generateBarcodeImage(String barcodeText) {
        try {
            // Tạo mã vạch từ chuỗi văn bản
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(barcodeText, BarcodeFormat.CODE_128, 300, 100);

            // Chuyển BitMatrix thành BufferedImage
            BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);  // Đen và trắng
                }
            }

            // Chuyển BufferedImage thành byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", baos);
            return baos.toByteArray();  // Trả về ảnh mã vạch dưới dạng byte[]
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
