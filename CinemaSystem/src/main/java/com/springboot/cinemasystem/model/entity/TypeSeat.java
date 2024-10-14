package com.springboot.cinemasystem.model.entity;

import java.util.*;

public class TypeSeat {

	List<Seat> seat;
	private int typeSeatID;
	private String name;
	private int surcharge;

	public int getTypeSeatID() {
		return this.typeSeatID;
	}

	/**
	 * 
	 * @param typeSeatID
	 */
	public void setTypeSeatID(int typeSeatID) {
		this.typeSeatID = typeSeatID;
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

	public int getSurcharge() {
		return this.surcharge;
	}

	/**
	 * 
	 * @param surcharge
	 */
	public void setSurcharge(int surcharge) {
		this.surcharge = surcharge;
	}

}