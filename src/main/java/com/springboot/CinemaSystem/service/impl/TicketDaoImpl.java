package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.SelectedSeatDto;
import com.springboot.CinemaSystem.dto.TypeDiscountDto;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.TicketDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDaoImpl implements TicketDao {
	private DayOfWeekRepository dayOfWeekRepository;
	private TimeFrameRepository timeFrameRepository;
	private TypeCustomerRepository typeCustomerRepository;
	private BasePriceRepository basePriceRepository;
	private SelectedSeatRepository selectedSeatRepository;
	private UserRepository userRepository;
	private SeatRepository seatRepository;
	private ShowtimeRepository showtimeRepository;
	private BookingRepository bookingRepository;
	private PayCashRepository payCashRepository;
	private PayTypeCustomerRepository payTypeCustomerRepository;
	private TicketRepository ticketRepository;
	private DiscountRepository discountRepository;
	private TypeDiscountRepository typeDiscountRepository;
	private SimpMessagingTemplate simpMessagingTemplate;


	@Autowired
	public TicketDaoImpl(DayOfWeekRepository dayOfWeekRepository, TimeFrameRepository timeFrameRepository, TypeCustomerRepository typeCustomerRepository, BasePriceRepository basePriceRepository, SelectedSeatRepository selectedSeatRepository, UserRepository userRepository, SeatRepository seatRepository, ShowtimeRepository showtimeRepository, BookingRepository bookingRepository, PayCashRepository payCashRepository, PayTypeCustomerRepository payTypeCustomerRepository, TicketRepository ticketRepository, DiscountRepository discountRepository, TypeDiscountRepository typeDiscountRepository, SimpMessagingTemplate simpMessagingTemplate) {
		this.dayOfWeekRepository = dayOfWeekRepository;
		this.timeFrameRepository = timeFrameRepository;
		this.typeCustomerRepository = typeCustomerRepository;
		this.basePriceRepository = basePriceRepository;
		this.selectedSeatRepository = selectedSeatRepository;
		this.userRepository = userRepository;
		this.seatRepository = seatRepository;
		this.showtimeRepository = showtimeRepository;
		this.bookingRepository = bookingRepository;
		this.payCashRepository = payCashRepository;
		this.payTypeCustomerRepository = payTypeCustomerRepository;
		this.ticketRepository = ticketRepository;
		this.discountRepository = discountRepository;
		this.typeDiscountRepository = typeDiscountRepository;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}





	@Override
	public List<TimeFrame> getAllTimeFrames() {
		try {
			return timeFrameRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error get all timeframe" + e.getMessage());
		}
	}

	@Override
	public List<DayOfWeek> getAllDayOfWeeks() {
		try {
			return dayOfWeekRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error get all day of week" + e.getMessage());
		}
	}

	@Override
	public List<TypeCustomer> getAllTypeCustomer() {
		try {
			return typeCustomerRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error get all typecustomer" + e.getMessage());
		}
	}

	@Override
	public BasePrice getBasePrice() {
		try {
			return basePriceRepository.findTopByOrderByIDDesc();
		} catch (Exception e) {
			throw new NotFoundException("Error get baseprice" + e.getMessage());
		}
	}

	@Override
	public TypeCustomer getTypeCustomerById(long id) {
		return typeCustomerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error get typecustomer by ID"));
	}

	@Override
	public void updateTypeCustomer(TypeCustomer typeCustomer) {
		if(!typeCustomerRepository.existsById(typeCustomer.getID())) {
			throw new NotFoundException("Error get typecustomer by id" + typeCustomer.getID());
		}
		typeCustomerRepository.save(typeCustomer);
	}

	@Override
	public DayOfWeek getDayOfWeekById(Long key) {
		return dayOfWeekRepository.findById(key)
				.orElseThrow(() -> new NotFoundException("Error get dayofweek by ID"));
	}

	@Override
	public void updateDayOfWeek(DayOfWeek dayOfWeek) {
		if(!dayOfWeekRepository.existsById(dayOfWeek.getID())){
			throw new NotFoundException("Error get dayofweek by ID");
		}
		dayOfWeekRepository.save(dayOfWeek);
	}

	@Override
	public TimeFrame getTimeFrameById(Long key) {
		return timeFrameRepository.findById(key)
				.orElseThrow(() -> new NotFoundException("Error timeframe by ID"));
	}

	@Override
	public void updateTimeFrame(TimeFrame timeFrame) {
		if(!timeFrameRepository.existsById(timeFrame.getID())){
			throw new NotFoundException("Error timeframe by ID");
		}
		timeFrameRepository.save(timeFrame);
	}

	@Override
	@Scheduled(fixedRate = 60000)
	@Transactional
	public void updateAllSelectedSeat() {
		LocalDateTime localDateTime = LocalDateTime.now();
		List<SelectedSeat> selectedSeats = selectedSeatRepository.getSelectedSeatByPending(localDateTime);
		for(SelectedSeat s : selectedSeats){
			this.updateStatusToExpired(s.getID());
		}
	}

	@Override
	public long addSelectedSeat(SelectedSeat selectedSeat) {
		try {
			long id = selectedSeatRepository.save(selectedSeat).getID();
			simpMessagingTemplate.convertAndSend(
					"/topic/showtime/" + selectedSeat.getShowtime().getID() + "/seat",
					new SelectedSeatDto(
							selectedSeat.getID(),
							selectedSeat.getUser().getID(),
							selectedSeat.getSeat().getID(),
							selectedSeat.getStart(),
							selectedSeat.getEnd(),
							selectedSeat.getStatus()
					)
			);
			return id;
		} catch (Exception e) {
			throw new DataProcessingException("Error add selectedSeat" + e.getMessage());
		}
	}

	@Override
	public SelectedSeat getSelectedSeatByID(long selectedSeatID) {
		return selectedSeatRepository.findById(selectedSeatID)
				.orElseThrow(() -> new NotFoundException("Error getSelectedSeatByID: " + selectedSeatID));
	}

	@Override
	public boolean updateStatusToExpired(long id) {
		SelectedSeat selectedSeat = selectedSeatRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error get SelectedSeat by id"));
		selectedSeat.setStatus("expired");
		try {
			selectedSeatRepository.save(selectedSeat);
			simpMessagingTemplate.convertAndSend(
					"/topic/showtime/" + selectedSeat.getShowtime().getID() + "/seat",
					new SelectedSeatDto(
							selectedSeat.getID(),
							selectedSeat.getUser().getID(),
							selectedSeat.getSeat().getID(),
							selectedSeat.getStart(),
							selectedSeat.getEnd(),
							selectedSeat.getStatus()
					)
			);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Error updateStatusToExpired" + e.getMessage());
		}
	}

	@Override
	public Booking addBooking(Booking booking) {
		try {
			return bookingRepository.save(booking);
		} catch (Exception e) {
			throw new DataProcessingException("Error addBooking" + e.getMessage());
		}
	}

	@Override
	public PayCash addPayCash(PayCash payCash) {
		try {
			return payCashRepository.save(payCash);
		} catch (Exception e) {
			throw new DataProcessingException("Error addPayCash: " + e.getMessage());
		}
	}

	@Override
	public void addPayTypeCustomer(List<PayTypeCustomer> payTypeCustomers) {
		try {
			payTypeCustomerRepository.saveAll(payTypeCustomers);
		} catch (Exception e) {
			throw new DataProcessingException("Error addPayTypeCustomer: " + e.getMessage());
		}
	}

	@Override
	public void addTicket(List<Ticket> tickets) {
		try {
			ticketRepository.saveAll(tickets);
		} catch (Exception e) {
			throw new DataProcessingException("Error addTicket: " + e.getMessage());
		}
	}

	@Override
	public Booking getTicketBoughtByID(int bookingID) {
		return null;
	}

	@Override
	public boolean addTicketBought(Booking booking) {
		return false;
	}

	@Override
	public List<Booking> getTicketBoughtByCustomer(int customerID) {
		return List.of();
	}
}