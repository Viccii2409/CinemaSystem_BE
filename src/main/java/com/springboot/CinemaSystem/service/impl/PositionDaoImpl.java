package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.Position;
import com.springboot.CinemaSystem.entity.Role;
import com.springboot.CinemaSystem.service.PositionDao;

import java.util.List;

public class PositionDaoImpl implements PositionDao {

	@Override
	public boolean addPosition(Position position) {
		return false;
	}

	@Override
	public boolean editPosition(Position position) {
		return false;
	}

	@Override
	public boolean updateStatusPosition(int positionID) {
		return false;
	}

	@Override
	public Position getPositionByID(int positionID) {
		return null;
	}

	@Override
	public List<Position> getAllPositions() {
		return List.of();
	}

	@Override
	public boolean assignPosition(int employeeID, int positionID) {
		return false;
	}

	@Override
	public boolean addRole(Role role) {
		return false;
	}

	@Override
	public boolean updateRole(Role role) {
		return false;
	}

	@Override
	public Role getRoleByID(int roleID) {
		return null;
	}

	@Override
	public List<Role> getAllRole() {
		return List.of();
	}
}