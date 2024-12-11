package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.*;
import jakarta.servlet.http.HttpServletResponse;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private TicketDao ticketDao;
    private TheaterDao theaterDao;
    private ShowtimeDao showtimeDao;
    private DiscountDao discountDao;
    private UserDao userDao;
    private FileStorageService fileStorageService;

    @Autowired
    public TicketController(TicketDao ticketDao, TheaterDao theaterDao, ShowtimeDao showtimeDao, DiscountDao discountDao, UserDao userDao, FileStorageService fileStorageService) {
        this.ticketDao = ticketDao;
        this.theaterDao = theaterDao;
        this.showtimeDao = showtimeDao;
        this.discountDao = discountDao;
        this.userDao = userDao;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/public/discount")
    public List<DiscountDto> getAllDiscount() {
        List<DiscountDto> discountDtos = new ArrayList<>();
        List<Discount> discounts = discountDao.getAllDiscounts();
        for(Discount discount : discounts) {
            discountDtos.add(discount.toDiscountDto());
        }
        return discountDtos;
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_PRICETICKET', 'MANAGER_SELLING')")
    @GetMapping("/typecustomer")
    public List<TypeCustomer> getTypeCustomer() {
        return ticketDao.getAllTypeCustomer();
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @GetMapping("/showtime")
    public List<TheaterMovieDto> getShowtime() {
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.valueOf(localDate);
        LocalTime localTime = LocalTime.now();
        Time currentTime = Time.valueOf(localTime);
        System.out.println(currentDate);
        System.out.println(currentTime);
        List<Theater> theaters = theaterDao.getAllTheaters();
        List<TheaterMovieDto> theaterMovieDtos = new ArrayList<>();
        for(Theater theater : theaters) {
//            theaterMovieDtos.add(theaterDao.getShowtimeByTheaterAndDateTime(theater, currentDate, currentTime));
            theaterMovieDtos.add(theaterDao.getShowtimeByTheater(theater));
        }
        return theaterMovieDtos;
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
        return ticketDao.getBookingById(id).toBookingDto2();
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @GetMapping("/booking/payonline/{barcode}")
    public BookingDto getBookingByBarcode(@PathVariable("barcode") String barcode) {
        return ticketDao.getBookingByBarcode(barcode).toBookingDto();
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PostMapping("/booking/payonline/add")
    @Transactional
    public String addPayOnline(@RequestBody PaymentRequestDto paymentRequestDto) {
        String orderId = "MOMO" + System.currentTimeMillis();
        createPayment(paymentRequestDto, orderId);
        String redirectUrl = "http://localhost:3000/view-booking";
        String payUrl = this.createPayOnline(orderId, paymentRequestDto.getAmount(), redirectUrl);
        return payUrl;
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'MANAGER_SELLING')")
    @PostMapping("/admin/booking/payonline/add")
    @Transactional
    public String addPayOnlineAdmin(@RequestBody PaymentRequestDto paymentRequestDto) {
        String orderId = "MOMO" + System.currentTimeMillis();
        createPayment(paymentRequestDto, orderId);
        String redirectUrl = "http://localhost:3000/admin/view-ticket-admin";
        String payUrl = this.createPayOnline(orderId, paymentRequestDto.getAmount(), redirectUrl);
        return payUrl;
    }

    @PreAuthorize("hasAuthority('MANAGER_SELLING')")
    @PostMapping("/booking/paycash/add")
    @Transactional
    public long addPayCash(@RequestBody PaymentRequestDto paymentRequestDto) {
        return this.createPayment(paymentRequestDto, null);
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
    public DiscountDto addDiscount(@ModelAttribute DiscountAddDto discountAddDto,
                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Discount discount = Discount.toDiscount(discountAddDto);
            TypeDiscount typeDiscount = discountDao.getTypeDiscountByID(discountAddDto.getTypeDiscountid());
            discount.setTypeDiscount(typeDiscount);
            discount.setStatus(true);
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageService.saveFileFromCloudinary(file);
                discount.setImage(imageUrl);
            }
            Discount discount_new = discountDao.addDiscount(discount);
            DiscountDto discountDto = discount_new.toDiscountDto();
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
    public DiscountDto updateDiscount(@ModelAttribute DiscountAddDto discountAddDto,
                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        Discount discount_old = discountDao.getDiscountByID(discountAddDto.getId());
        Discount discount = Discount.toDiscount(discountAddDto);
        TypeDiscount typeDiscount = discountDao.getTypeDiscountByID(discountAddDto.getTypeDiscountid());
        discount.setTypeDiscount(typeDiscount);
        discount.setImage(discount_old.getImage());
        discount.setUser(discount_old.getUser());
        discount.setPayment(discount_old.getPayment());
        if(file != null && !file.isEmpty()){
            String imageUrl = fileStorageService.updateFile(file, discount_old.getImage());
            discount.setImage(imageUrl);
        }
        Discount discount_new = discountDao.updateDiscount(discount);
        return discount_new.toDiscountDto();
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @DeleteMapping("/discount/{id}/delete")
    public boolean deleteDiscount(@PathVariable("id") long id) {
        Discount discount = discountDao.getDiscountByID(id);
        fileStorageService.deleteFileFromCloudinary(discount.getImage());
        return discountDao.deleteDiscount(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_DISCOUNT')")
    @GetMapping("/typediscount")
    public List<TypeDiscountDto> getTypeDiscountDtos() {
        return discountDao.getAllTypeDiscount();
    }

//    @PostMapping("/public/momo/ipn")
//    public void payOnlineIpn() {
//        System.out.println("12345678");
//    }

    private long createPayment(PaymentRequestDto paymentRequestDto, String orderId) {
        Booking booking = new Booking();
        Showtime showtime = showtimeDao.getShowtimeByID(paymentRequestDto.getShowtimeid());
        booking.setShowtime(showtime);
        User user = userDao.getUserByID(paymentRequestDto.getUserid());
        booking.setUser(user);
        booking.setTypeBooking(paymentRequestDto.getTypeBooking());
        Booking booking_new = ticketDao.addBooking(booking);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketRequestDto dto : paymentRequestDto.getTicket()) {
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
        if(paymentRequestDto.getTypePay().equals("PAYCASH")) {
            PayCash payCash = new PayCash();
            payCash.setTotalPrice(paymentRequestDto.getTotalPrice());
            payCash.setDiscountPrice(paymentRequestDto.getDiscountPrice());
            payCash.setAmount(paymentRequestDto.getAmount());
            payCash.setStatus("confirmed");
            payCash.setBooking(booking_new_2);
            payCash.setReceived(paymentRequestDto.getReceived());
            payCash.setMoneyReturned(paymentRequestDto.getMoneyReturned());
            PayCash payCash_new = ticketDao.addPayCash(payCash);
            paymentID = payCash_new.getID();
        }
        else if (paymentRequestDto.getTypePay().equals("PAYONLINE")) {
            PayOnline payOnline = new PayOnline();
            payOnline.setTotalPrice(paymentRequestDto.getTotalPrice());
            payOnline.setDiscountPrice(paymentRequestDto.getDiscountPrice());
            payOnline.setAmount(paymentRequestDto.getAmount());
            payOnline.setBarcode(orderId);
            payOnline.setStatus("pending");
            payOnline.setBooking(booking_new_2);
            if(paymentRequestDto.getDiscountid() > 0){
                Discount discount = discountDao.getDiscountByID(paymentRequestDto.getDiscountid());
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
        Map<Long, Map<Long, Integer>> paytypecustomermap = paymentRequestDto.getPaytypecustomer();
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