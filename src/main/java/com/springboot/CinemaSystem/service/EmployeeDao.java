package com.springboot.CinemaSystem.service;

import com.springboot.CinemaSystem.entity.*;

import java.util.List;

public interface EmployeeDao {
	public boolean addEmployee(Employee employee);
	public boolean editEmployee(Employee employee);
	public Employee getEmployeeByID(int employeeID);
	public boolean updateStatus(int employeeID);
	public List<Employee> getAllEmployee();
	public List<Employee> getEmployeeByPosition(int positionID);

	public boolean addAdmin(Admin admin);
	public boolean updateAdmin(Admin admin);
	public Admin getAdminByID(int adminID);

	public boolean addManager(Manager manager);
	public boolean updateManager(Manager manager);
	public Manager getManagerByID(int managerID);
	public List<Manager> getAllManager();

	public boolean addCustomerService(CustomerService customerService);
	public boolean editCustomerService(CustomerService customerService);
	public CustomerService getCustomerService(int customerServiceID);

	public boolean addAgent(Agent agent);
	public boolean updateAgent(Agent agent);
	public Agent getAgent(int agentID);
	public List<Agent> getAllAgent();


}