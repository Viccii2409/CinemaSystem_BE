package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.BookingDto;
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
	private BookingRepository bookingRepository;
	private PayCashRepository payCashRepository;
	private PayTypeCustomerRepository payTypeCustomerRepository;
	private TicketRepository ticketRepository;
	private PayOnlineRepository payOnlineRepository;
	private SimpMessagingTemplate simpMessagingTemplate;


	@Autowired
	public TicketDaoImpl(DayOfWeekRepository dayOfWeekRepository, TimeFrameRepository timeFrameRepository, TypeCustomerRepository typeCustomerRepository, BasePriceRepository basePriceRepository, SelectedSeatRepository selectedSeatRepository, BookingRepository bookingRepository, PayCashRepository payCashRepository, PayTypeCustomerRepository payTypeCustomerRepository, TicketRepository ticketRepository, PayOnlineRepository payOnlineRepository, SimpMessagingTemplate simpMessagingTemplate) {
		this.dayOfWeekRepository = dayOfWeekRepository;
		this.timeFrameRepository = timeFrameRepository;
		this.typeCustomerRepository = typeCustomerRepository;
		this.basePriceRepository = basePriceRepository;
		this.selectedSeatRepository = selectedSeatRepository;
		this.bookingRepository = bookingRepository;
		this.payCashRepository = payCashRepository;
		this.payTypeCustomerRepository = payTypeCustomerRepository;
		this.ticketRepository = ticketRepository;
		this.payOnlineRepository = payOnlineRepository;
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
	public Booking getBookingById(long id) {
		return bookingRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getBookingById: " + id));
	}

	@Override
	public PayOnline addPayOnline(PayOnline payOnline) {
		try {
			return payOnlineRepository.save(payOnline);
		} catch (Exception e) {
			throw new DataProcessingException("Error addPayOnline: " + e.getMessage());
		}
	}

	@Override
	public PayOnline getPayOnlineByBarcode(String orderId) {
		try {
			return payOnlineRepository.findByBarcode(orderId);
		} catch (Exception e) {
			throw new NotFoundException("Error getPayOnlineByBarcode: " + orderId);
		}
	}

	@Override
	public PayOnline updatePayOnline(PayOnline payOnline) {
		if(!payOnlineRepository.existsById(payOnline.getID())){
			throw new NotFoundException("Error updatePayOnline by ID");
		}
		return payOnlineRepository.save(payOnline);
	}

	@Override
	public void updateSelectSeatStatusToExpired(long showtimeID, long userid, long seatid) {
		try {
			selectedSeatRepository.updateSeatStatusToExpired(showtimeID, userid, seatid);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Booking getBookingByBarcode(String barcode) {
		PayOnline payOnline = this.getPayOnlineByBarcode(barcode);
		return payOnline.getBooking();
	}

	@Override
	@Scheduled(fixedRate = 60000)
	@Transactional
	public void updateStatusPayOnlineToExpired() {
		try {
			LocalDateTime localDateTime = LocalDateTime.now();
			List<PayOnline> payOnlines = payOnlineRepository.getPayOnlineActive(localDateTime);
			for(PayOnline payOnline : payOnlines) {
				payOnline.setStatus("expired");
				this.updatePayOnline(payOnline);
				long showtimeID = payOnline.getBooking().getShowtime().getID();
				long userid = 0;
				if(payOnline.getAgent() != null) {
					userid = payOnline.getAgent().getID();
				}
				else if(payOnline.getBooking().getCustomer() != null) {
					userid = payOnline.getBooking().getCustomer().getID();
				}
				for(Ticket ticket : payOnline.getBooking().getTicket()) {
					this.updateSelectSeatStatusToExpired(showtimeID, userid, ticket.getSeat().getID());
				}
			}
		} catch (Exception e) {
			throw new DataProcessingException("Error updateStatusPayOnlineToExpired: " + e.getMessage());
		}
	}
}