package com.springboot.CinemaSystem.service.impl;


import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.EmployeeDao;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public boolean addEmployee(Employee employee) {
		return false;
	}

	@Override
	public boolean editEmployee(Employee employee) {
		return false;
	}

	@Override
	public Employee getEmployeeByID(int employeeID) {
		return null;
	}

	@Override
	public boolean updateStatus(int employeeID) {
		return false;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return List.of();
	}

	@Override
	public List<Employee> getEmployeeByPosition(int positionID) {
		return List.of();
	}

	@Override
	public boolean addAdmin(Admin admin) {
		return false;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return false;
	}

	@Override
	public Admin getAdminByID(int adminID) {
		return null;
	}

	@Override
	public boolean addManager(Manager manager) {
		return false;
	}

	@Override
	public boolean updateManager(Manager manager) {
		return false;
	}

	@Override
	public Manager getManagerByID(int managerID) {
		return null;
	}

	@Override
	public List<Manager> getAllManager() {
		return List.of();
	}

	@Override
	public boolean addCustomerService(CustomerService customerService) {
		return false;
	}

	@Override
	public boolean editCustomerService(CustomerService customerService) {
		return false;
	}

	@Override
	public CustomerService getCustomerService(int customerServiceID) {
		return null;
	}

	@Override
	public boolean addAgent(Agent agent) {
		return false;
	}

	@Override
	public boolean updateAgent(Agent agent) {
		return false;
	}

	@Override
	public Agent getAgent(int agentID) {
		return null;
	}

	@Override
	public List<Agent> getAllAgent() {
		return List.of();
	}
}