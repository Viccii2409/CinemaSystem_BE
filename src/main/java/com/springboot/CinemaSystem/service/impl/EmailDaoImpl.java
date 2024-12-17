package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.BookingDto;
import com.springboot.CinemaSystem.service.EmailDao;
import com.springboot.CinemaSystem.util.BarcodeUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static com.springboot.CinemaSystem.util.BarcodeUtil.generateBarcodeImage;

@Service
public class EmailDaoImpl implements EmailDao {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);  // true để gửi email HTML

            javaMailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setEmailwithBarcode(String to, String subject, String text, String barcode) {
        try {
            // Tạo hình ảnh Barcode
            byte[] barcodeImage = generateBarcodeImage(barcode);
            String encodedBarcode = Base64.getEncoder().encodeToString(barcodeImage);

            // Tạo email MIME
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            // Đính kèm hình ảnh Barcode dưới dạng inline (hiển thị trong nội dung HTML)
            helper.addInline("barcode", new ByteArrayResource(barcodeImage), "image/png");

            // Gửi email
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String generateTicketHtmlWithBarcode(BookingDto ticketInfo) {
        // Định dạng ngày và giờ
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DecimalFormat currencyFormat = new DecimalFormat("#,### VND");

        // Tạo nội dung HTML động với thông tin vé
        String htmlContent = "<div style='font-family: Arial, sans-serif; margin: 20px;'>"
                + "<h2 style='color: #4CAF50;'>Thông tin vé của bạn</h2>"

                // Thông tin khách hàng
                + "<h3>Thông tin khách hàng</h3>"
                + "<p><strong>Khách hàng:</strong> " + (ticketInfo.getNameCustomer() != null ? ticketInfo.getNameCustomer() : "N/A") + "</p>"
                + "<p><strong>Số điện thoại:</strong> " + (ticketInfo.getPhone() != null ? ticketInfo.getPhone() : "N/A") + "</p>"
                + "<p><strong>Email:</strong> " + (ticketInfo.getEmail() != null ? ticketInfo.getEmail() : "N/A") + "</p>"

                // Thông tin vé
                + "<div style='border: 1px solid #ccc; padding: 15px; margin-bottom: 15px;'>"
                + "<h3>Thông tin vé</h3>"
                + "<p><strong>Mã vé:</strong> " + (ticketInfo.getBarcode() != null ? ticketInfo.getBarcode() : "N/A") + "</p>"
                + "<p><strong>Tên Phim:</strong> " + (ticketInfo.getNameMovie() != null ? ticketInfo.getNameMovie() : "N/A") + "</p>"
                + "<p><strong>Phòng Chiếu:</strong> " + (ticketInfo.getNameRoom() != null ? ticketInfo.getNameRoom() : "N/A") + " - " + (ticketInfo.getTypeRoom() != null ? ticketInfo.getTypeRoom() : "N/A") + "</p>"
                + "<p><strong>Ngày chiếu:</strong> " + (ticketInfo.getDateShowtime() != null ? ticketInfo.getDateShowtime().toLocalDate() : "N/A") + "</p>"
                + "<p><strong>Giờ chiếu:</strong> " + (ticketInfo.getStartTime() != null ? ticketInfo.getStartTime() : "N/A") + " - " + (ticketInfo.getEndTime() != null ? ticketInfo.getEndTime() : "N/A") + "</p>"
                + "<p><strong>Ghế ngồi:</strong> " + (ticketInfo.getNameSeats() != null && !ticketInfo.getNameSeats().isEmpty() ? String.join(", ", ticketInfo.getNameSeats()) : "Không có ghế ngồi") + "</p>"
                + "<p><strong>Ngày đặt vé:</strong> " + (ticketInfo.getDateBooking() != null ? ticketInfo.getDateBooking().format(dateTimeFormatter) : "N/A") + "</p>"
                + "<p><strong>Tên rạp:</strong> " + (ticketInfo.getNameTheater() != null ? ticketInfo.getNameTheater() : "N/A") + "</p>"
                + "<p><strong>Địa chỉ rạp:</strong> " + (ticketInfo.getAddress() != null ? ticketInfo.getAddress() : "N/A") + "</p>"
                + "<p><strong>Tổng tiền:</strong> " + (ticketInfo.getTotalPrice() > 0 ? currencyFormat.format(ticketInfo.getTotalPrice()) : "0") + "</p>"
                + "<p><strong>Giảm giá:</strong> " + (ticketInfo.getDiscountPrice() > 0 ? currencyFormat.format(ticketInfo.getDiscountPrice()) : "0") + "</p>"
                + "<p><strong>Thành tiền:</strong> " + (ticketInfo.getAmount() > 0 ? currencyFormat.format(ticketInfo.getAmount()) : "0") + "</p>"
                + "<p><strong>Mã Barcode:</strong> <img src='cid:barcode' alt='Barcode' /></p>"
                + "</div>"
                + "</div>";

        return htmlContent;
    }

}
