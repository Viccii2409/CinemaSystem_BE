package com.springboot.CinemaSystem.service.impl;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.service.PdfDao;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.File;


@Service
public class PdfDaoImpl implements PdfDao {

//    @Override
//    public void generateMovieTicketPdf(BookingDto dto) {
//        String userHome = System.getProperty("user.home");
//        String downloadPath = userHome + File.separator + "Downloads" + File.separator + "movie_ticket.pdf";
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
//        PdfDocument pdfDocument = new PdfDocument(writer);
//        Document document = new Document(pdfDocument);
//        try {
//            PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
//            document.add(new Paragraph("Movie Ticket")
//                    .setFont(font)
//                    .setFontSize(24));
//
//            document.add(new Paragraph("-----------------------------------------------------"));
//
//            document.add(new Paragraph("Movie Name: " + dto.getNameMovie()));
//            document.add(new Paragraph("Show Time: " + dto.getStartTime()));
//
//            document.add(new Paragraph("-----------------------------------------------------"));
//
//            document.add(new Paragraph("Thank you for your purchase!"));
//            document.close();
//
//            try (FileOutputStream fileOutputStream = new FileOutputStream(downloadPath)) {
//                byteArrayOutputStream.writeTo(fileOutputStream);
//            }
//
//            System.out.println("PDF file has been saved to: " + downloadPath);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void generateMovieTicketPdf(BookingDto dto) {
//        String userHome = System.getProperty("user.home");
//        String downloadPath = userHome + File.separator + "Downloads" + File.separator + "movie_ticket.pdf";
        // Đường dẫn lưu file PDF trong thư mục Downloads

        String userHome = System.getProperty("user.home");
        String baseFileName = "ve_xem_phim.pdf";
        String downloadPath = userHome + File.separator + "Downloads" + File.separator;

        // Đường dẫn đầy đủ ban đầu
        String filePath = downloadPath + baseFileName;

        // Kiểm tra file đã tồn tại và sinh tên file mới nếu cần
        File file = new File(filePath);
        int count = 1;

        while (file.exists()) {
            filePath = downloadPath + "ve_xem_phim_" + count + ".pdf";
            file = new File(filePath);
            count++;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        PageSize pageSize = PageSize.A6;
        Document document = new Document(pdfDocument, pageSize);
        String fontPath = "src/main/resources/fonts/NotoSans-Regular.ttf";

        try {
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

            for(String name: dto.getNameSeats()) {
                document.add(new Paragraph(dto.getNameTheater())
                        .setFont(font)
                        .setFontSize(14)
                        .setTextAlignment(TextAlignment.CENTER));

                document.add(new Paragraph(dto.getAddress())
                        .setFont(font)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER));

                document.add(new Paragraph("-----------------------------------------------------"));

                document.add(new Paragraph("Ngày đặt vé: " + dto.getDateBooking())
                        .setFont(font)
                        .setFontSize(10));
                document.add(new Paragraph("Tên phim: " + dto.getNameMovie())
                        .setFont(font)
                        .setFontSize(10));
                document.add(new Paragraph("Lịch chiếu: " + dto.getDateShowtime() + " " + dto.getStartTime())
                        .setFont(font)
                        .setFontSize(10));
                document.add(new Paragraph("Phòng chiếu: " + dto.getNameRoom())
                        .setFont(font)
                        .setFontSize(10));
                document.add(new Paragraph("Ghế ngồi: " + name)
                        .setFont(font)
                        .setFontSize(10));

                document.add(new Paragraph("-----------------------------------------------------"));

                document.add(new Paragraph("Chúc bạn xem phim vui vẻ!")
                        .setFont(font));

                if (!name.equals(dto.getNameSeats().get(dto.getNameSeats().size() - 1))) {
                    document.add(new AreaBreak());
                }
            }
            document.close();

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                byteArrayOutputStream.writeTo(fileOutputStream);
            }

            System.out.println("PDF file has been saved to: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
