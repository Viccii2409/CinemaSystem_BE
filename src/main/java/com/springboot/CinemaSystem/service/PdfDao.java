package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.BookingDto;

public interface PdfDao {
    void generateMovieTicketPdf(BookingDto dto);
}
