package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.TypeDiscount;
import com.springboot.CinemaSystem.service.DiscountDao;

import java.util.List;

public class DiscountDaoImpl implements DiscountDao {

	@Override
	public boolean addDiscount(Discount discount) {
		return false;
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		return false;
	}

	@Override
	public boolean updateStatusDiscount(int discountID) {
		return false;
	}

	@Override
	public Discount getDiscountByID(int discountID) {
		return null;
	}

	@Override
	public List<Discount> getAllDiscounts() {
		return List.of();
	}

	@Override
	public boolean validateDiscount(String discountCode) {
		return false;
	}

	@Override
	public Discount getDiscountByCode(String discountCode) {
		return null;
	}

	@Override
	public List<Discount> getDiscountsByType(int typeDiscountID) {
		return List.of();
	}

	@Override
	public boolean addTypeDiscount(TypeDiscount typeDiscount) {
		return false;
	}

	@Override
	public boolean editTypeDiscount(TypeDiscount typeDiscount) {
		return false;
	}

	@Override
	public TypeDiscount getTypeDiscountByID(int typeDiscountID) {
		return null;
	}

	@Override
	public List<TypeDiscount> getAllTypeDiscounts() {
		return List.of();
	}
}