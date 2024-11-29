package com.springboot.CinemaSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/timeframe")
    public List<TimeFrame> getTimeFrame() {
        return ticketDao.getAllTimeFrames();
    }

    @GetMapping("/dayofweek")
    public List<DayOfWeek> getDayOfWeek() {
        return ticketDao.getAllDayOfWeeks();
    }

    @GetMapping("/typecustomer")
    public List<TypeCustomer> getTypeCustomer() {
        return ticketDao.getAllTypeCustomer();
    }

    @GetMapping("/typediscount")
    public List<TypeDiscountDto> getTypeDiscountDtos() {
        return discountDao.getAllTypeDiscount();
    }

    @GetMapping("/baseprice")
    public BasePrice getBasePrice() {
        return ticketDao.getBasePrice();
    }

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
                DayOfWeek dayOfWeek = ticketDao.getDayOfWeekById(key);
                dayOfWeek.setSurcharge(value);
                ticketDao.updateDayOfWeek(dayOfWeek);
            } catch (Exception e) {
                throw new DataProcessingException("Error update price " + e.getMessage());
            }
        }

        for (Map.Entry<String, Object> entry : timeFrameMap.entrySet()) {
            try {
                Long key = Long.parseLong(entry.getKey());
                Float value = Float.parseFloat((String) entry.getValue());
                TimeFrame timeFrame = ticketDao.getTimeFrameById(key);
                timeFrame.setSurcharge(value);
                ticketDao.updateTimeFrame(timeFrame);
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

    @GetMapping("/showtime/{id}")
    public ShowtimeRoomDto getShowtimeRoomDto(@PathVariable("id") long id) {
        return showtimeDao.getRoomByShowtime(id);
    }

    @PostMapping("/showtime/selectedseat/reserve")
    public long addSelectedSeat(@RequestBody SelectedSeat selectedSeat) {
        System.out.println(selectedSeat);
        return ticketDao.addSelectedSeat(selectedSeat);
    }

    @PutMapping("/showtime/selectedseat/{id}/expired")
    public boolean updateSelectedSeat(@PathVariable("id") long id) {
        return ticketDao.updateStatusToExpired(id);
    }

    @PostMapping("/booking/paycash/add")
    @Transactional
    public long addPayCash(@RequestBody PayCashRequestDto payCashRequestDto) {
        Booking booking = new Booking();
//        LocalDateTime localDateTime = LocalDateTime.now();
        Showtime showtime = showtimeDao.getShowtimeByID(payCashRequestDto.getShowtimeid());
//        booking.setDate(localDateTime);
        booking.setShowtime(showtime);
        Booking booking_new = ticketDao.addBooking(booking);

        PayCash payCash = new PayCash();
        payCash.setBooking(booking_new);
//        payCash.setDate(localDateTime);
        payCash.setStatus("confirmed");
        payCash.setTotalPrice(payCashRequestDto.getTotalPrice());
        payCash.setDiscountPrice(payCashRequestDto.getDiscountPrice());
        payCash.setAmount(payCashRequestDto.getAmount());
        Agent agent = new Agent();
        agent.setID(payCashRequestDto.getAgentid());
        payCash.setAgent(agent);
        payCash.setReceived(payCashRequestDto.getReceived());
        payCash.setMoneyReturned(payCashRequestDto.getMoneyReturned());
        PayCash payCash_new = ticketDao.addPayCash(payCash);

        List<PayTypeCustomer> payTypeCustomers = new ArrayList<>();
        Map<Long, Map<Long, Integer>> paytypecustomermap = payCashRequestDto.getPaytypecustomer();
        if (paytypecustomermap != null) {
            paytypecustomermap.forEach((key, valueMap) -> {
                TypeCustomer typeCustomer = new TypeCustomer();
                typeCustomer.setID(key);
                valueMap.forEach((subKey, subValue) -> {
                    PayTypeCustomer payTypeCustomer = new PayTypeCustomer();
                    payTypeCustomer.setCount(subValue);
                    TypeSeat typeSeat = new TypeSeat();
                    typeSeat.setID(subKey);
                    payTypeCustomer.setTypeSeat(typeSeat);
                    payTypeCustomer.setTypeCustomer(typeCustomer);
                    payTypeCustomer.setPayment(payCash_new);
                    payTypeCustomers.add(payTypeCustomer);
                });
            });
        }
        ticketDao.addPayTypeCustomer(payTypeCustomers);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketRequestDto dto : payCashRequestDto.getTicket()) {
            SelectedSeat selectedSeat = ticketDao.getSelectedSeatByID(dto.getSelectedSeatID());
            selectedSeat.setStatus("confirmed");
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            seat.setID(dto.getId());
            ticket.setSeat(seat);
            ticket.setBooking(booking_new);
            tickets.add(ticket);
        }
        ticketDao.addTicket(tickets);
        return booking.getID();
    }

    @PostMapping("/customer/booking/paycash/add")
    @Transactional
    public long addPayCashCustomer(@RequestBody PayCashRequestDto payCashRequestDto) {
        Booking booking = new Booking();
        Showtime showtime = showtimeDao.getShowtimeByID(payCashRequestDto.getShowtimeid());
        booking.setShowtime(showtime);
        Customer customer = userDao.getCustomerById(payCashRequestDto.getCustomerid());
        booking.setCustomer(customer);
        Booking booking_new = ticketDao.addBooking(booking);

        PayCash payCash = new PayCash();
        payCash.setStatus("confirmed");
        payCash.setBooking(booking_new);
        payCash.setTotalPrice(payCashRequestDto.getTotalPrice());
        payCash.setDiscountPrice(payCashRequestDto.getDiscountPrice());
        payCash.setAmount(payCashRequestDto.getAmount());

        payCash.setReceived(payCashRequestDto.getReceived());
        payCash.setMoneyReturned(payCashRequestDto.getMoneyReturned());
        if(payCashRequestDto.getDiscountid() > 0) {
            Discount discount = discountDao.getDiscountByID(payCashRequestDto.getDiscountid());
            payCash.setDiscount(discount);
            customer.getDiscount().add(discount);
            userDao.updateCustomer(customer);
        }
        PayCash payCash_new = ticketDao.addPayCash(payCash);

        List<PayTypeCustomer> payTypeCustomers = new ArrayList<>();
        Map<Long, Map<Long, Integer>> paytypecustomermap = payCashRequestDto.getPaytypecustomer();
        if (paytypecustomermap != null) {
            paytypecustomermap.forEach((key, valueMap) -> {
                TypeCustomer typeCustomer = new TypeCustomer();
                typeCustomer.setID(key);
                valueMap.forEach((subKey, subValue) -> {
                    PayTypeCustomer payTypeCustomer = new PayTypeCustomer();
                    payTypeCustomer.setCount(subValue);
                    TypeSeat typeSeat = new TypeSeat();
                    typeSeat.setID(subKey);
                    payTypeCustomer.setTypeSeat(typeSeat);
                    payTypeCustomer.setTypeCustomer(typeCustomer);
                    payTypeCustomer.setPayment(payCash_new);
                    payTypeCustomers.add(payTypeCustomer);
                });
            });
        }
        ticketDao.addPayTypeCustomer(payTypeCustomers);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketRequestDto dto : payCashRequestDto.getTicket()) {
            SelectedSeat selectedSeat = ticketDao.getSelectedSeatByID(dto.getSelectedSeatID());
            selectedSeat.setStatus("confirmed");
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            seat.setID(dto.getId());
            ticket.setSeat(seat);
            ticket.setBooking(booking_new);
            tickets.add(ticket);
        }
        ticketDao.addTicket(tickets);


        return booking.getID();
    }

    @GetMapping("/discount")
    public List<DiscountDto> getAllDiscount() {
        List<DiscountDto> discountDtos = new ArrayList<>();
        List<Discount> discounts = discountDao.getAllDiscounts();
        for(Discount discount : discounts) {
            discountDtos.add(discount.toDiscountDto());
        }
        return discountDtos;
    }

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

    @PutMapping("/discount/{id}/updatestatus")
    public boolean updateStatusDiscount(@PathVariable("id") long id) {
        Discount discount = discountDao.getDiscountByID(id);
        discount.setStatus(!discount.isStatus());
        Discount discount_new = discountDao.updateDiscount(discount);
        return true;
    }

    @PutMapping("/discount/update")
    public DiscountDto updateDiscount(@ModelAttribute DiscountAddDto discountAddDto,
                                      @RequestParam(value = "file", required = false) MultipartFile file) {
        Discount discount_old = discountDao.getDiscountByID(discountAddDto.getId());
        Discount discount = Discount.toDiscount(discountAddDto);
        TypeDiscount typeDiscount = discountDao.getTypeDiscountByID(discountAddDto.getTypeDiscountid());
        discount.setTypeDiscount(typeDiscount);
        discount.setImage(discount_old.getImage());
        discount.setCustomer(discount_old.getCustomer());
        discount.setPayment(discount_old.getPayment());
        if(file != null && !file.isEmpty()){
            String imageUrl = fileStorageService.updateFile(file, discount_old.getImage());
            discount.setImage(imageUrl);
        }
        Discount discount_new = discountDao.updateDiscount(discount);
        return discount_new.toDiscountDto();
    }

    @DeleteMapping("/discount/{id}/delete")
    public boolean deleteDiscount(@PathVariable("id") long id) {
        Discount discount = discountDao.getDiscountByID(id);
        fileStorageService.deleteFileFromCloudinary(discount.getImage());
        return discountDao.deleteDiscount(id);
    }

    @GetMapping("/booking/{id}")
    public BookingDto getBooking(@PathVariable("id") long id) {
        return ticketDao.getBookingById(id).toBookingDto();
    }

    @GetMapping("/booking/payonline/{barcode}")
    public BookingDto getBookingByBarcode(@PathVariable("barcode") String barcode) {
        return ticketDao.getBookingByBarcode(barcode).toBookingDto();
    }

    @PostMapping("/customer/booking/payonline")
    @Transactional
    public String addPayOnlineCustomer(@RequestBody PayOnlineRequestDto payOnlineRequestDto, HttpServletResponse response) {
        Booking booking = new Booking();
        Showtime showtime = showtimeDao.getShowtimeByID(payOnlineRequestDto.getShowtimeid());
        booking.setShowtime(showtime);
        Customer customer = userDao.getCustomerById(payOnlineRequestDto.getCustomerid());
        booking.setCustomer(customer);
        Booking booking_new = ticketDao.addBooking(booking);

        long amount = payOnlineRequestDto.getAmount();

        if (amount < 1000 || amount > 50000000) {
            throw new DataProcessingException("Số tiền không hợp lệ. Vui lòng nhập số tiền từ 1,000 VND đến 50,000,000 VND.");
        }
        String orderId = "MOMO" + System.currentTimeMillis();
        PayOnline payOnline = new PayOnline();
        payOnline.setBarcode(orderId);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = localDateTime.plusHours(1).plusMinutes(40);
        payOnline.setDateExpire(updatedDateTime);
        payOnline.setStatus("pending");
        payOnline.setBooking(booking_new);
        payOnline.setTotalPrice(payOnlineRequestDto.getTotalPrice());
        payOnline.setDiscountPrice(payOnlineRequestDto.getDiscountPrice());
        payOnline.setAmount(payOnlineRequestDto.getAmount());

        if(payOnlineRequestDto.getDiscountid() > 0) {
            Discount discount = discountDao.getDiscountByID(payOnlineRequestDto.getDiscountid());
            payOnline.setDiscount(discount);
            customer.getDiscount().add(discount);
            userDao.updateCustomer(customer);
        }
        PayOnline payOnline_new = ticketDao.addPayOnline(payOnline);

        List<PayTypeCustomer> payTypeCustomers = new ArrayList<>();
        Map<Long, Map<Long, Integer>> paytypecustomermap = payOnlineRequestDto.getPaytypecustomer();
        if (paytypecustomermap != null) {
            paytypecustomermap.forEach((key, valueMap) -> {
                TypeCustomer typeCustomer = new TypeCustomer();
                typeCustomer.setID(key);
                valueMap.forEach((subKey, subValue) -> {
                    PayTypeCustomer payTypeCustomer = new PayTypeCustomer();
                    payTypeCustomer.setCount(subValue);
                    TypeSeat typeSeat = new TypeSeat();
                    typeSeat.setID(subKey);
                    payTypeCustomer.setTypeSeat(typeSeat);
                    payTypeCustomer.setTypeCustomer(typeCustomer);
                    payTypeCustomer.setPayment(payOnline_new);
                    payTypeCustomers.add(payTypeCustomer);
                });
            });
        }
        ticketDao.addPayTypeCustomer(payTypeCustomers);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketRequestDto dto : payOnlineRequestDto.getTicket()) {
            SelectedSeat selectedSeat = ticketDao.getSelectedSeatByID(dto.getSelectedSeatID());
            selectedSeat.setStatus("confirmed");
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            seat.setID(dto.getId());
            ticket.setSeat(seat);
            ticket.setBooking(booking_new);
            tickets.add(ticket);
        }
        ticketDao.addTicket(tickets);

        String redirectUrl = "http://localhost:3000/view-booking";
        String payUrl = this.createPayOnline(orderId, amount, redirectUrl);
        return payUrl;
    }

    @PostMapping("/booking/payonline")
    @Transactional
    public String addPayOnline(@RequestBody PayOnlineRequestDto payOnlineRequestDto, HttpServletResponse response) {
        Booking booking = new Booking();
        Showtime showtime = showtimeDao.getShowtimeByID(payOnlineRequestDto.getShowtimeid());
        booking.setShowtime(showtime);
        Booking booking_new = ticketDao.addBooking(booking);

        long amount = payOnlineRequestDto.getAmount();

        if (amount < 1000 || amount > 50000000) {
            throw new DataProcessingException("Số tiền không hợp lệ. Vui lòng nhập số tiền từ 1,000 VND đến 50,000,000 VND.");
        }
        String orderId = "MOMO" + System.currentTimeMillis();
        PayOnline payOnline = new PayOnline();
        payOnline.setBarcode(orderId);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = localDateTime.plusHours(1).plusMinutes(40);
//        LocalDateTime updatedDateTime = localDateTime.plusMinutes(2);
        payOnline.setDateExpire(updatedDateTime);
        payOnline.setStatus("pending");
        payOnline.setBooking(booking_new);
        payOnline.setTotalPrice(payOnlineRequestDto.getTotalPrice());
        payOnline.setDiscountPrice(payOnlineRequestDto.getDiscountPrice());
        payOnline.setAmount(payOnlineRequestDto.getAmount());
        Agent agent = new Agent();
        agent.setID(payOnlineRequestDto.getAgentid());
        payOnline.setAgent(agent);
        PayOnline payOnline_new = ticketDao.addPayOnline(payOnline);

        List<PayTypeCustomer> payTypeCustomers = new ArrayList<>();
        Map<Long, Map<Long, Integer>> paytypecustomermap = payOnlineRequestDto.getPaytypecustomer();
        if (paytypecustomermap != null) {
            paytypecustomermap.forEach((key, valueMap) -> {
                TypeCustomer typeCustomer = new TypeCustomer();
                typeCustomer.setID(key);
                valueMap.forEach((subKey, subValue) -> {
                    PayTypeCustomer payTypeCustomer = new PayTypeCustomer();
                    payTypeCustomer.setCount(subValue);
                    TypeSeat typeSeat = new TypeSeat();
                    typeSeat.setID(subKey);
                    payTypeCustomer.setTypeSeat(typeSeat);
                    payTypeCustomer.setTypeCustomer(typeCustomer);
                    payTypeCustomer.setPayment(payOnline_new);
                    payTypeCustomers.add(payTypeCustomer);
                });
            });
        }
        ticketDao.addPayTypeCustomer(payTypeCustomers);

        List<Ticket> tickets = new ArrayList<>();
        for(TicketRequestDto dto : payOnlineRequestDto.getTicket()) {
            SelectedSeat selectedSeat = ticketDao.getSelectedSeatByID(dto.getSelectedSeatID());
            selectedSeat.setStatus("confirmed");
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            seat.setID(dto.getId());
            ticket.setSeat(seat);
            ticket.setBooking(booking_new);
            tickets.add(ticket);
        }
        ticketDao.addTicket(tickets);

        String redirectUrl = "http://localhost:3000/admin/view-ticket-admin";
        String payUrl = this.createPayOnline(orderId, amount, redirectUrl);
        return payUrl;
    }

    @PostMapping("/booking/payonline/{barcode}")
    public String createPayOnlineAfter(@PathVariable("barcode") String barcode) {
        PayOnline payOnline = ticketDao.getPayOnlineByBarcode(barcode);
        String redirectUrl = "http://localhost:3000/view-booking";
        String payUrl = this.createPayOnline(barcode, (long) payOnline.getAmount(), redirectUrl);
        return payUrl;
    }

    private String createPayOnline(String orderId, long amount, String redirect_url) {
        try {// Tạo mã giao dịch duy nhất
            String requestId = orderId;
            String orderInfo = "Thanh toán đơn hàng: " + orderId;
            String partnerCode = "MOMO";
            String accessKey = "F8BBA842ECF85";
            String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
            String redirectUrl = redirect_url;
            String ipnUrl = "http://localhost:8080/api/ticket/booking/payonline/ipn";
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


    @PutMapping("/booking/payonline/update/{barcode}/{status}")
    public void updatePayOnline(@PathVariable("barcode") String barcode,
                                @PathVariable("status") int status) {
        System.out.println(barcode + " " + status);
        if (status == 0) {
            LocalDateTime localDateTime = LocalDateTime.now();
            PayOnline payOnline = ticketDao.getPayOnlineByBarcode(barcode);
            payOnline.setStatus("confirmed");
            payOnline.setDate(localDateTime);
            PayOnline payOnline_new = ticketDao.updatePayOnline(payOnline);
        }
        else {
            PayOnline payOnline = ticketDao.getPayOnlineByBarcode(barcode);
            payOnline.setStatus("expired");
            PayOnline payOnline_new = ticketDao.updatePayOnline(payOnline);
            long showtimeID = payOnline_new.getBooking().getShowtime().getID();
            long userid = 0;
            if(payOnline_new.getAgent() != null) {
                userid = payOnline_new.getAgent().getID();
            }
            else if(payOnline_new.getBooking().getCustomer() != null) {
                userid = payOnline_new.getBooking().getCustomer().getID();
            }
            for(Ticket ticket : payOnline_new.getBooking().getTicket()) {
                ticketDao.updateSelectSeatStatusToExpired(showtimeID, userid, ticket.getSeat().getID());
            }
        }
    }





}