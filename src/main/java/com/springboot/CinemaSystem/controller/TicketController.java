package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.*;
import jakarta.transaction.Transactional;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private TicketDao ticketDao;
    private TheaterDao theaterDao;
    private ShowtimeDao showtimeDao;
    private DiscountDao discountDao;
    private UserDao userDao;
    private FileStorageDao fileStorageDao;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private PdfDao pdfDao;

    @Autowired
    public TicketController(TicketDao ticketDao, TheaterDao theaterDao, ShowtimeDao showtimeDao, DiscountDao discountDao, UserDao userDao, FileStorageDao fileStorageDao) {
        this.ticketDao = ticketDao;
        this.theaterDao = theaterDao;
        this.showtimeDao = showtimeDao;
        this.discountDao = discountDao;
        this.userDao = userDao;
        this.fileStorageDao = fileStorageDao;
    }

    @GetMapping("/public/discount")
    public List<DiscountDto> getAllDiscount() {
        return discountDao.getAllDiscounts().stream()
                .map(entry -> DiscountDto.toDiscountDto(entry))
                .collect(Collectors.toList());
    }

    @GetMapping("public/discount/active")
    public List<DiscountDto> getAllDiscountActive() {
        java.util.Date currentDate = new java.util.Date();
        Date date = new Date(currentDate.getTime());
        return discountDao.getAllDiscounts().stream()
                .filter(entry -> entry.isStatus()
                        && !entry.getStart().after(date)
                        && !entry.getEnd().before(date)
                        )
                .map(entry -> DiscountDto.toDiscountDto(entry))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_PRICETICKET', 'MANAGER_SELLING')")
    @GetMapping("/typecustomer")
    public List<TypeCustomer> getTypeCustomer() {
        return ticketDao.getAllTypeCustomer();
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @GetMapping("/showtime")
    public List<TheaterDto> getShowtime() {
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.valueOf(localDate);
        LocalTime localTime = LocalTime.now();
        Time currentTime = Time.valueOf(localTime);
        List<Theater> theaters = theaterDao.getAllTheater();
        List<TheaterDto> dto = new ArrayList<>();
        for(Theater theater : theaters) {
            dto.add(theaterDao.getShowtimeByTheater(theater));
        }
        return dto;
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @GetMapping("/showtime/{id}")
    public ShowtimeRoomDto getShowtimeRoomDto(@PathVariable("id") long id) {
        return showtimeDao.getRoomByShowtime(id);
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PostMapping("/showtime/selectedseat/reserve")
    public long addSelectedSeat(@RequestBody SelectedSeat selectedSeat) {
        System.out.println(selectedSeat);
        return ticketDao.addSelectedSeat(selectedSeat);
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PutMapping("/showtime/selectedseat/{id}/expired")
    public boolean updateSelectedSeat(@PathVariable("id") long id) {
        return ticketDao.updateStatusToExpired(id);
    }

    @GetMapping("/booking/{id}")
    public BookingDto getBooking(@PathVariable("id") long id) {
        return BookingDto.toBookingDto2(ticketDao.getBookingById(id));
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @GetMapping("/booking/payonline/{barcode}")
    public BookingDto getBookingByBarcode(@PathVariable("barcode") String barcode) {
        return BookingDto.toBookingDto(ticketDao.getBookingByBarcode(barcode));
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PostMapping("/booking/payonline/add")
    @Transactional
    public String addPayOnline(@RequestBody PaymentDto paymentDto) {
        String orderId = "MOMO" + System.currentTimeMillis();
        createPayment(paymentDto, orderId);
        String redirectUrl = "http://localhost:3000/view-booking";
        String payUrl = this.createPayOnline(orderId, paymentDto.getAmount(), redirectUrl);
        return payUrl;
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PostMapping("/admin/booking/payonline/add")
    @Transactional
    public String addPayOnlineAdmin(@RequestBody PaymentDto paymentDto) {
        String orderId = "MOMO" + System.currentTimeMillis();
        createPayment(paymentDto, orderId);
        String redirectUrl = "http://localhost:3000/admin/view-ticket-admin";
        String payUrl = this.createPayOnline(orderId, paymentDto.getAmount(), redirectUrl);
        return payUrl;
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @PostMapping("/booking/paycash/add")
    @Transactional
    public long addPayCash(@RequestBody PaymentDto paymentDto) {
        return this.createPayment(paymentDto, null);
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING', 'VIEW_CUSTOMER_INFOR')")
    @PutMapping("/booking/payonline/update/{barcode}/{status}")
    public void updatePayOnline(@PathVariable("barcode") String barcode,
                                @PathVariable("status") int status) {
        Payment payment = ticketDao.getPaymentByBarcode(barcode);
        if (status == 0) {
            LocalDateTime localDateTime = LocalDateTime.now();
            payment.setStatus("confirmed");
            payment.setDate(localDateTime);
            ticketDao.updatePayment(payment);

            BookingDto booking = BookingDto.toBookingDto(payment.getBooking());
            String to = payment.getBooking().getEmailCustomer();
            String subject = "LAL Cinema - Thông tin vé của bạn";
            String text = emailDao.generateTicketHtmlWithBarcode(booking);
            emailDao.setEmailwithBarcode(to, subject, text, booking.getBarcode());
        }
        else {
            payment.setStatus("expired");
            Payment payment_new = ticketDao.updatePayment(payment);
            long showtimeID = payment_new.getBooking().getShowtime().getID();
            long userID = payment_new.getBooking().getUser().getID();
            for(Ticket ticket : payment_new.getBooking().getTicket()) {
                ticketDao.updateSelectSeatStatusToExpired(showtimeID, userID, ticket.getSeat().getID());
            }
        }
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @GetMapping("/booking/search/{barcode}")
    public BookingDto searchBooking(@PathVariable("barcode") String barcode) {
        Booking booking = ticketDao.getBookingByBarcode(barcode);
        if(booking != null) {
            return BookingDto.toBookingDto(booking);
        }
        return null;
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @GetMapping("/booking/export/{id}")
    public boolean exportBooking(@PathVariable("id") long id) {
        Booking booking = ticketDao.getBookingById(id);
        pdfDao.generateMovieTicketPdf(BookingDto.toBookingDto(booking));
        return true;
    }

    @PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PostMapping("/booking/payonline/add/{barcode}")
    public String createPayOnlineAfter(@PathVariable("barcode") String barcode) {
        Payment payment = ticketDao.getPaymentByBarcode(barcode);
        String redirectUrl = "http://localhost:3000/view-booking";
        return this.createPayOnline(barcode, (long) payment.getAmount(), redirectUrl);
    }

    @PreAuthorize("hasAuthority('MANAGER_PRICETICKET')")
    @PutMapping("/baseprice/update")
    public boolean updatePrice(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        Map<String, Object> typeCustomerMap = (Map<String, Object>) data.get("typeCustomer");
        Map<String, Object> dayOfWeekMap = (Map<String, Object>) data.get("dayOfWeek");
        Map<String, Object> timeFrameMap = (Map<String, Object>) data.get("timeFrame");
        Map<String, Object> typeRoomMap = (Map<String, Object>) data.get("typeRoom");
        Map<String, Object> typeSeatMap = (Map<String, Object>) data.get("typeSeat");

        for (Map.Entry<String, Object> entry : typeCustomerMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                TypeCustomer typeCustomer = ticketDao.getTypeCustomerById(key);
                typeCustomer.setDiscount(value);
                ticketDao.updateTypeCustomer(typeCustomer);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        for (Map.Entry<String, Object> entry : dayOfWeekMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                DayOfWeek dayOfWeek = showtimeDao.getDayOfWeekById(key);
                dayOfWeek.setSurcharge(value);
                showtimeDao.updateDayOfWeek(dayOfWeek);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        for (Map.Entry<String, Object> entry : timeFrameMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                TimeFrame timeFrame = showtimeDao.getTimeFrameById(key);
                timeFrame.setSurcharge(value);
                showtimeDao.updateTimeFrame(timeFrame);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        for (Map.Entry<String, Object> entry : typeRoomMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                TypeRoom typeRoom = theaterDao.getTypeRoomByID(key);
                typeRoom.setSurcharge(value);
                theaterDao.updateTypeRoom(typeRoom);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        for (Map.Entry<String, Object> entry : typeSeatMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                TypeSeat typeSeat = theaterDao.getTypeSeatByID(key);
                typeSeat.setSurcharge(value);
                theaterDao.updateTypeSeat(typeSeat);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        return true;
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @PostMapping("/discount/add")
    public DiscountDto addDiscount(@ModelAttribute DiscountDto dto,
                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Discount discount = Discount.toDiscount(dto);
            TypeDiscount typeDiscount = discountDao.getTypeDiscountByID(dto.getTypeDiscountid());
            discount.setTypeDiscount(typeDiscount);
            discount.setStatus(true);
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageDao.saveFileFromCloudinary(file, "Image/Discount", "image");
                discount.setImage(imageUrl);
            }
            Discount discount_new = discountDao.addDiscount(discount);
            DiscountDto discountDto = DiscountDto.toDiscountDto(discount_new);
            return discountDto;
        } catch (Exception e) {
            throw new DataProcessingException("Error addDiscount: " + e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @PutMapping("/discount/{id}/updatestatus")
    public boolean updateStatusDiscount(@PathVariable("id") long id) {
        Discount discount = discountDao.getDiscountByID(id);
        discount.setStatus(!discount.isStatus());
        Discount discount_new = discountDao.updateDiscount(discount);
        return true;
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @PutMapping("/discount/update")
    public DiscountDto updateDiscount(@ModelAttribute DiscountDto dto,
                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        Discount discount_old = discountDao.getDiscountByID(dto.getId());
        Discount discount = Discount.toDiscount(dto);
        TypeDiscount typeDiscount = discountDao.getTypeDiscountByID(dto.getTypeDiscountid());
        discount.setTypeDiscount(typeDiscount);
        discount.setImage(discount_old.getImage());
        discount.setUser(discount_old.getUser());
        discount.setPayment(discount_old.getPayment());
        if(file != null && !file.isEmpty()){
            String imageUrl = fileStorageDao.updateFile(file, discount_old.getImage(), "Image/Discount", "image");
            discount.setImage(imageUrl);
        }
        Discount discount_new = discountDao.updateDiscount(discount);
        return DiscountDto.toDiscountDto(discount_new);
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @DeleteMapping("/discount/{id}/delete")
    public boolean deleteDiscount(@PathVariable("id") long id) {
        Discount discount = discountDao.getDiscountByID(id);
        fileStorageDao.deleteFileFromCloudinary(discount.getImage(), "Image/Discount");
        return discountDao.deleteDiscount(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @GetMapping("/typediscount")
    public List<TypeDiscountDto> getTypeDiscountDtos() {
        return discountDao.getAllTypeDiscount().stream()
                .map(entry -> TypeDiscountDto.toTypeDiscountDto(entry))
                .collect(Collectors.toList());
    }

    @Transactional
    private long createPayment(PaymentDto paymentDto, String orderId) {
        Booking booking = new Booking();
        booking.setTypeBooking(paymentDto.getTypeBooking());
        if(paymentDto.getTypeBooking().equals("ONLINE")){
            booking.setNameCustomer(paymentDto.getNameCustomer());
            booking.setEmailCustomer(paymentDto.getEmailCustomer());
            booking.setPhoneCustomer(paymentDto.getPhoneCustomer());
        }
        booking.setStatus(true);
        Showtime showtime = showtimeDao.getShowtimeByID(paymentDto.getShowtimeid());
        booking.setShowtime(showtime);
        User user = userDao.getUserByID(paymentDto.getUserid());
        booking.setUser(user);
        Booking booking_new = ticketDao.addBooking(booking);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketDto dto : paymentDto.getTicket()) {
            SelectedSeat selectedSeat = ticketDao.getSelectedSeatByID(dto.getSelectedSeatID());
            selectedSeat.setStatus("confirmed");
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            seat.setID(dto.getId());
            ticket.setSeat(seat);
            ticket.setBooking(booking_new);
            tickets.add(ticket);
        }
        booking_new.getTicket().clear();
        booking_new.getTicket().addAll(tickets);
        Booking booking_new_2 = ticketDao.updateBooking(booking_new);
//        ticketDao.addTicket(tickets);
        long paymentID;
        if(paymentDto.getTypePay().equals("PAYCASH")) {
            PayCash payCash = new PayCash();
            payCash.setTotalPrice(paymentDto.getTotalPrice());
            payCash.setDiscountPrice(paymentDto.getDiscountPrice());
            payCash.setAmount(paymentDto.getAmount());
            payCash.setStatus("confirmed");
            payCash.setBooking(booking_new_2);
            payCash.setReceived(paymentDto.getReceived());
            payCash.setMoneyReturned(paymentDto.getMoneyReturned());
            PayCash payCash_new = ticketDao.addPayCash(payCash);
            paymentID = payCash_new.getID();
        }
        else if (paymentDto.getTypePay().equals("PAYONLINE")) {
            PayOnline payOnline = new PayOnline();
            payOnline.setTotalPrice(paymentDto.getTotalPrice());
            payOnline.setDiscountPrice(paymentDto.getDiscountPrice());
            payOnline.setAmount(paymentDto.getAmount());
            payOnline.setBarcode(orderId);
            payOnline.setStatus("pending");
            payOnline.setBooking(booking_new_2);
            if(paymentDto.getDiscountid() > 0){
                Discount discount = discountDao.getDiscountByID(paymentDto.getDiscountid());
                user.getDiscount().add(discount);
                userDao.updateUser(user);
                payOnline.setDiscount(discount);
            }
            LocalDateTime dateExpire = LocalDateTime.now().plusHours(1).plusMinutes(40);
            payOnline.setDateExpire(dateExpire);
            System.out.println(payOnline);
            PayOnline payOnline_new = ticketDao.addPayOnline(payOnline);
            paymentID = payOnline_new.getID();
        } else {
            paymentID = -1;
        }
        System.out.println(paymentID);
        List<PayTypeCustomer> payTypeCustomers = new ArrayList<>();
        Map<Long, Map<Long, Integer>> paytypecustomermap = paymentDto.getPaytypecustomer();
        if (paytypecustomermap != null) {
            paytypecustomermap.forEach((key, valueMap) -> {
                TypeCustomer typeCustomer = new TypeCustomer(key);
                valueMap.forEach((subKey, subValue) -> {
                    PayTypeCustomer payTypeCustomer = new PayTypeCustomer();
                    payTypeCustomer.setCount(subValue);
                    payTypeCustomer.setTypeSeat(new TypeSeat(subKey));
                    payTypeCustomer.setTypeCustomer(typeCustomer);
                    payTypeCustomer.setPayment(new Payment(paymentID));
                    payTypeCustomers.add(payTypeCustomer);
                });
            });
        }
        System.out.println(payTypeCustomers);
        ticketDao.addPayTypeCustomer(payTypeCustomers);
        return booking_new.getID();
    }

    private String createPayOnline(String orderId, long amount, String redirect_url) {
        try {// Tạo mã giao dịch duy nhất
            String requestId = orderId;
            String orderInfo = "Thanh toán đơn hàng: " + orderId;
            String partnerCode = "MOMO";
            String accessKey = "F8BBA842ECF85";
            String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
            String redirectUrl = redirect_url;
            String ipnUrl = "http://localhost:8080/api/ticket/public/momo/ipn";
            String requestType = "payWithMethod";   // captureWallet: payQR
            String extraData = "";

            // Tạo raw signature
            String rawSignature = String.format(
                    "accessKey=%s&amount=%d&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                    accessKey, amount, extraData, ipnUrl, orderId, orderInfo, partnerCode, redirectUrl, requestId, requestType
            );

            // Ký HMAC SHA256
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Hmac.init(secretKeySpec);
            byte[] hash = sha256Hmac.doFinal(rawSignature.getBytes(StandardCharsets.UTF_8));
            StringBuilder signatureBuilder = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) signatureBuilder.append('0');
                signatureBuilder.append(hex);
            }
            String signature = signatureBuilder.toString();

            // Tạo body request
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("partnerCode", partnerCode);
            requestBody.put("partnerName", "Test");
            requestBody.put("storeId", "MomoTestStore");
            requestBody.put("requestId", requestId);
            requestBody.put("amount", amount);
            requestBody.put("orderId", orderId);
            requestBody.put("orderInfo", orderInfo);
            requestBody.put("redirectUrl", redirectUrl);
            requestBody.put("ipnUrl", ipnUrl);
            requestBody.put("requestType", requestType);
            requestBody.put("extraData", extraData);
            requestBody.put("signature", signature);

            // Gửi request đến MoMo
            String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(endpoint);
            httpPost.setHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(
                    new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(requestBody),
                    StandardCharsets.UTF_8
            );
            httpPost.setEntity(entity);

            try (CloseableHttpResponse momoResponse = httpClient.execute(httpPost)) {
                String responseString = EntityUtils.toString(momoResponse.getEntity());
                Map<String, Object> responseMap = new com.fasterxml.jackson.databind.ObjectMapper().readValue(responseString, Map.class);

                if (responseMap.containsKey("payUrl")) {
                    String paymentUrl = responseMap.get("payUrl").toString();
//                    response.sendRedirect(paymentUrl);
                    return paymentUrl;
                } else {
                    System.err.println("MoMo response does not contain payUrl");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}