package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.TypeDiscount;

import java.util.List;

public interface DiscountDao {
	public boolean addDiscount(Discount discount);
	public boolean updateDiscount(Discount discount);
	public boolean updateStatusDiscount(int discountID);
	public Discount getDiscountByID(int discountID);
	public List<Discount> getAllDiscounts();
	public boolean validateDiscount(String discountCode);
	public Discount getDiscountByCode(String discountCode);
	public List<Discount> getDiscountsByType(int typeDiscountID);

	public boolean addTypeDiscount(TypeDiscount typeDiscount);
	public boolean editTypeDiscount(TypeDiscount typeDiscount);
	public TypeDiscount getTypeDiscountByID(int typeDiscountID);
	public List<TypeDiscount> getAllTypeDiscounts();


}