package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.Position;
import com.springboot.CinemaSystem.entity.Role;

import java.util.List;

public interface PositionDao {
	public boolean addPosition(Position position);
	public boolean editPosition(Position position);
	public boolean updateStatusPosition(int positionID);
	public Position getPositionByID(int positionID);
	public List<Position> getAllPositions();
	public boolean assignPosition(int employeeID, int positionID);
	public boolean addRole(Role role);
	public boolean updateRole(Role role);
	public Role getRoleByID(int roleID);
	public List<Role> getAllRole();


}