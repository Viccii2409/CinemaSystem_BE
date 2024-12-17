package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.dto.TypeDiscountDto;
import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.TypeDiscount;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.DiscountRepository;
import com.springboot.CinemaSystem.repository.TypeDiscountRepository;
import com.springboot.CinemaSystem.service.DiscountDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class DiscountDaoImpl implements DiscountDao {
	private DiscountRepository discountRepository;
	private TypeDiscountRepository typeDiscountRepository;

	@Autowired
	public DiscountDaoImpl(DiscountRepository discountRepository, TypeDiscountRepository typeDiscountRepository) {
		this.discountRepository = discountRepository;
		this.typeDiscountRepository = typeDiscountRepository;
	}

	@Override
	@Scheduled(fixedRate = 60000*60*24)
	@Transactional
	public void updateStatusDiscount() {
		LocalDate localDate = LocalDate.now();
		Date currentDate = Date.valueOf(localDate);
		try {
			List<Discount> discounts = discountRepository.getActiveDiscounts(currentDate);
			for(Discount discount: discounts){
				discount.setStatus(!discount.isStatus());
				this.updateDiscount(discount);
			}
		} catch (Exception e) {
			throw new DataProcessingException("Error updateStatusDiscount: " + e.getMessage());
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