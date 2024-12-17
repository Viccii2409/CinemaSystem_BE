package com.springboot.CinemaSystem.dto;

import lombok.AllArgsConstructor;
import com.springboot.CinemaSystem.entity.Booking;
import com.springboot.CinemaSystem.entity.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private long ID;
    private LocalDateTime dateBooking;
    private String barcode;
    private String typeBooking;
    private boolean statusBooking;


    private List<String> nameSeats;

    private Date dateShowtime;
    private Time startTime;
    private Time endTime;

    private long movieID;
    private String nameMovie;
    private String image;

    private String nameTheater;
    private String address;
    private String nameRoom;
    private String typeRoom;

    private float totalPrice;
    private float discountPrice;
    private float amount;

    private String nameCustomer;
    private String imageCustomer;
    private String phone;
    private String email;


    private String barcodePayment;
    private String statusPayment;
    private FeedbackDto feedback;

    public BookingDto(long ID, LocalDateTime dateBooking, String barcode, List<String> nameSeats, Date dateShowtime, Time startTime, Time endTime,long movieID, String nameMovie, String image, String nameTheater, String address, String nameRoom, String typeRoom, float totalPrice, float discountPrice, float amount, String nameCustomer, String phone, String email, boolean statusBooking) {
        this.ID = ID;
        this.dateBooking = dateBooking;
        this.barcode = barcode;
        this.nameSeats = nameSeats;
        this.dateShowtime = dateShowtime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieID=movieID;
        this.nameMovie = nameMovie;
        this.image = image;
        this.nameTheater = nameTheater;
        this.address = address;
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.amount = amount;
        this.nameCustomer = nameCustomer;
        this.phone = phone;
        this.email = email;
        this.statusBooking = statusBooking;
    }

    public BookingDto(long ID, long movieID,String nameCustomer,String imageCustomer) {
        this.ID=ID;
        this.movieID=movieID;
        this.nameCustomer=nameCustomer;
        this.imageCustomer=imageCustomer;
    }

    public static BookingDto toBookingDto(Booking booking) {
        List<String> nameSeats = new ArrayList<>();
        for (Ticket t : booking.getTicket()) {
            nameSeats.add(t.getSeat().getName());
        }
        BookingDto dto = new BookingDto(
                booking.getID(),
                booking.getDate(),
                booking.getBarcode(),
                nameSeats,
                booking.getShowtime().getDate(),
                booking.getShowtime().getStartTime(),
                booking.getShowtime().getEndTime(),
                booking.getShowtime().getMovie().getId(),
                booking.getShowtime().getMovie().getTitle(),
                booking.getShowtime().getMovie().getFirstImage(),
                booking.getShowtime().getRoom().getTheater().getName(),
                booking.getShowtime().getRoom().getTheater().getFullAddress(),
                booking.getShowtime().getRoom().getName(),
                booking.getShowtime().getRoom().getTypeRoom().getName(),
                booking.getPayment().getTotalPrice(),
                booking.getPayment().getDiscountPrice(),
                booking.getPayment().getAmount(),
                (booking.getNameCustomer() != null) ? booking.getNameCustomer() : "",
                (booking.getEmailCustomer() != null) ? booking.getEmailCustomer() : "",
                (booking.getPhoneCustomer() != null) ? booking.getPhoneCustomer() : "",
                booking.isStatus()
        );
        return dto;
    }

    public static BookingDto toBookingDto2 (Booking booking) {
        BookingDto dto = toBookingDto(booking);
        dto.setBarcodePayment(booking.getPayment().getBarcode());
        dto.setStatusPayment(booking.getPayment().getStatus());
        if(booking.getFeedback() != null) {
            dto.setFeedback(FeedbackDto.toFeedbackDto(booking.getFeedback()));
        }
        dto.setTypeBooking(booking.getTypeBooking());
        return dto;
    }
}