package com.springboot.cinemasystem.model.entity;

import java.util.*;

public class Director {

	List<Movie> movie;
	private int directorID;
	private String name;

	public int getDirectorID() {
		return this.directorID;
	}

	/**
	 * 
	 * @param directorID
	 */
	public void setDirectorID(int directorID) {
		this.directorID = directorID;
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