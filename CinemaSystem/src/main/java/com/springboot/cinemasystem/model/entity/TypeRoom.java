package com.springboot.cinemasystem.model.entity;

import java.util.*;



public class TypeRoom {

	List<Room> room;
	List<PriceTicket> priceticket;
	private int typeRoomID;
	private String name;

	public int getTypeRoomID() {
		return this.typeRoomID;
	}

	/**
	 * 
	 * @param typeRoomID
	 */
	public void setTypeRoomID(int typeRoomID) {
		this.typeRoomID = typeRoomID;
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

}