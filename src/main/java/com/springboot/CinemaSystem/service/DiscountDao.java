package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.dto.TypeDiscountDto;
import com.springboot.CinemaSystem.entity.Discount;
import com.springboot.CinemaSystem.entity.TypeDiscount;

import java.util.List;

public interface DiscountDao {
	public void updateStatusDiscount();
	public Discount addDiscount(Discount discount);
	public Discount getDiscountByID(long discountID);
	public Discount updateDiscount(Discount discount);
	public List<Discount> getAllDiscounts();
	public boolean deleteDiscount(long id);

	public List<TypeDiscountDto> getAllTypeDiscount();
	public TypeDiscount getTypeDiscountByID(long typeDiscountID);

	public boolean updateStatusDiscount(int discountID);
	public boolean validateDiscount(String discountCode);
	public Discount getDiscountByCode(String discountCode);
	public List<Discount> getDiscountsByType(int typeDiscountID);

	public boolean addTypeDiscount(TypeDiscount typeDiscount);
	public boolean editTypeDiscount(TypeDiscount typeDiscount);
	public List<TypeDiscount> getAllTypeDiscounts();


}