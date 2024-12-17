package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.BookingDto;

public interface EmailDao {
    public void sendEmail(String to, String subject, String text);

    public void setEmailwithBarcode(String to, String subject, String text, String barcode);
    public String generateTicketHtmlWithBarcode(BookingDto ticketInfo);
}
