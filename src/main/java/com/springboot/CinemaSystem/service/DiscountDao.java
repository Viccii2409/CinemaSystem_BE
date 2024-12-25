package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.TypeDiscount;

import java.util.List;

public interface DiscountDao {
	public Discount addDiscount(Discount discount);
	public Discount getDiscountByID(long discountID);
	public Discount updateDiscount(Discount discount);
	public List<Discount> getAllDiscounts();
	public boolean deleteDiscount(long id);

	public List<TypeDiscount> getAllTypeDiscount();
	public TypeDiscount getTypeDiscountByID(long typeDiscountID);
}