package com.springboot.cinemasystem.model.entity;

import java.util.*;

public class TypeUser {

	List<PriceTicket> priceticket;
	private int typeUserID;
	private String name;
	private int ageStart;
	private int ageEnd;

	public int getTypeUserID() {
		return this.typeUserID;
	}

	/**
	 * 
	 * @param typeUserID
	 */
	public void setTypeUserID(int typeUserID) {
		this.typeUserID = typeUserID;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getAgeStart() {
		return this.ageStart;
	}

	/**
	 * 
	 * @param ageStart
	 */
	public void setAgeStart(int ageStart) {
		this.ageStart = ageStart;
	}

	public int getAgeEnd() {
		return this.ageEnd;
	}

	/**
	 * 
	 * @param ageEnd
	 */
	public void setAgeEnd(int ageEnd) {
		this.ageEnd = ageEnd;
	}

}