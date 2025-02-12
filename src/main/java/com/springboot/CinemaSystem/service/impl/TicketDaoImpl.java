package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.SelectedSeatDto;
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
	private PaymentRepository paymentRepository;
	private DiscountRepository discountRepository;
	private TypeDiscountRepository typeDiscountRepository;


	@Autowired
	public TicketDaoImpl(DayOfWeekRepository dayOfWeekRepository, TimeFrameRepository timeFrameRepository, TypeCustomerRepository typeCustomerRepository, BasePriceRepository basePriceRepository, SelectedSeatRepository selectedSeatRepository, BookingRepository bookingRepository, PayCashRepository payCashRepository, PayTypeCustomerRepository payTypeCustomerRepository, TicketRepository ticketRepository, PayOnlineRepository payOnlineRepository, SimpMessagingTemplate simpMessagingTemplate, PaymentRepository paymentRepository, DiscountRepository discountRepository, TypeDiscountRepository typeDiscountRepository) {
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
		this.paymentRepository = paymentRepository;
		this.discountRepository = discountRepository;
		this.typeDiscountRepository = typeDiscountRepository;
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
	@Scheduled(fixedRate = 60000)
	@Transactional
	public void updateStatusPayOnlineToExpired() {
		try {
			LocalDateTime localDateTime = LocalDateTime.now();
			List<PayOnline> payOnlines = payOnlineRepository.getPayOnlineActive(localDateTime);
			for(PayOnline payOnline : payOnlines) {
				Payment payment = payOnline;
				payment.setStatus("expired");
				this.updatePayment(payment);
				long showtimeID = payOnline.getBooking().getShowtime().getID();
				long userid = payment.getBooking().getUser().getID();
				for(Ticket ticket : payOnline.getBooking().getTicket()) {
					this.updateSelectSeatStatusToExpired(showtimeID, userid, ticket.getSeat().getID());
				}
			}
		} catch (Exception e) {
			throw new DataProcessingException("Error updateStatusPayOnlineToExpired: " + e.getMessage());
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
	public long addSelectedSeat(SelectedSeat selectedSeat) {
		try {
			long id = selectedSeatRepository.save(selectedSeat).getID();
			simpMessagingTemplate.convertAndSend(
					"/topic/showtime/" + selectedSeat.getShowtime().getID() + "/seat",
					SelectedSeatDto.toSelectedSeatDto(selectedSeat)
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
					SelectedSeatDto.toSelectedSeatDto(selectedSeat)
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
	public Booking updateBooking(Booking booking) {
		if(!bookingRepository.existsById(booking.getID())){
			throw new NotFoundException("Not found booking: " + booking.getID());
		}
		try {
			return bookingRepository.save(booking);
		} catch (Exception e) {
			throw new DataProcessingException("Error updateBooking" + e.getMessage());
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
	public Booking getBookingById(long id) {
		return bookingRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getBookingById: " + id));
	}

	@Override
	public Payment updatePayment(Payment paymentNew) {
		if(!paymentRepository.existsById(paymentNew.getID())) {
			throw new NotFoundException("Not found payment: " + paymentNew.getID());
		}
		try {
			return paymentRepository.save(paymentNew);
		} catch (Exception e) {
			throw new DataProcessingException("Error addPayment: " + e.getMessage());
		}
	}

	@Override
	public Payment getPaymentByBarcode(String barcode) {
		try {
			return paymentRepository.findByBarcode(barcode);
		} catch (Exception e) {
			throw new NotFoundException("Error getPaymentByBarcode: " + barcode);
		}
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
	public void updateSelectSeatStatusToExpired(long showtimeID, long userid, long seatid) {
		try {
			selectedSeatRepository.updateSeatStatusToExpired(showtimeID, userid, seatid);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Booking getBookingByBarcode(String barcode) {
		try {
			return bookingRepository.findByBarcode(barcode);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Discount addDiscount(Discount discount) {
		try {
			return discountRepository.save(discount);
		} catch (Exception e) {
			throw new DataProcessingException("Error addDiscount: " + e.getMessage());
		}
	}

	@Override
	public Discount getDiscountByID(long discountID) {
		return discountRepository.findById(discountID)
				.orElseThrow(() -> new NotFoundException("Error getDiscountByID: " + discountID));
	}

	@Override
	public Discount updateDiscount(Discount discount) {
		if(!discountRepository.existsById(discount.getID())) {
			throw new NotFoundException("Error updateDiscount");
		}
		try {
			return discountRepository.save(discount);
		} catch (Exception e) {
			throw new DataProcessingException("Error updateDiscount: " + e.getMessage());
		}
	}

	@Override
	public List<Discount> getAllDiscounts() {
		try {
			return discountRepository.findAll();
		} catch (Exception e) {
			throw new DataProcessingException("Error getAllDiscounts: " + e.getMessage());
		}
	}

	@Override
	public boolean deleteDiscount(long id) {
		Discount discount = this.getDiscountByID(id);
		try {
			discountRepository.delete(discount);
			return true;
		} catch (Exception e) {
			throw new DataProcessingException("Error deleteDiscount: " + e.getMessage());
		}
	}

	@Override
	public List<TypeDiscount> getAllTypeDiscount() {
		try {
			return typeDiscountRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error getAllTypeDiscount: " + e.getMessage());
		}
	}

	@Override
	public TypeDiscount getTypeDiscountByID(long typeDiscountID) {
		return typeDiscountRepository.findById(typeDiscountID)
				.orElseThrow(() -> new NotFoundException("Error getTypeDiscountByID: " + typeDiscountID));
	}
}