package com.springboot.CinemaSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public boolean addPayCash(@RequestBody PayCashRequestDto payCashRequestDto) {
        Booking booking = new Booking();
        LocalDateTime localDateTime = LocalDateTime.now();
        Showtime showtime = showtimeDao.getShowtimeByID(payCashRequestDto.getShowtimeid());
        booking.setDate(localDateTime);
        booking.setShowtime(showtime);
        Booking booking_new = ticketDao.addBooking(booking);

        PayCash payCash = new PayCash();
        payCash.setBooking(booking_new);
        payCash.setDate(localDateTime);
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
        return true;
    }

    @PostMapping("/customer/booking/paycash/add")
    @Transactional
    public long addPayCashCustomer(@RequestBody PayCashRequestDto payCashRequestDto) {
        Booking booking = new Booking();
        LocalDateTime localDateTime = LocalDateTime.now();
        booking.setDate(localDateTime);
        Showtime showtime = showtimeDao.getShowtimeByID(payCashRequestDto.getShowtimeid());
        booking.setShowtime(showtime);
        Customer customer = userDao.getCustomerById(payCashRequestDto.getCustomerid());
        booking.setCustomer(customer);
        Booking booking_new = ticketDao.addBooking(booking);

        PayCash payCash = new PayCash();
        payCash.setBooking(booking_new);
        payCash.setDate(localDateTime);
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

}