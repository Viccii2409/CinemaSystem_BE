package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.dto.CustomerDto;
import com.springboot.CinemaSystem.entity.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
	public User getUserByID(long userID);
	public void updateUser(User user);
	public Customer getCustomerById(long id);
	public Customer addCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<CustomerDto> getAllCustomers();
	public UserDetails loadUserByUsername(String username);
	public User getUserByUsername(String username);

	List<Employee> getAllEmployee();
	Employee updateEmployee(Employee employee);

	public List<Role> getAllRole();

	public Role getRoleById(long id);
	Role addRole(Role role);
	Role updateRole(Role role);
	void deleteRole(Role role);

	public Level getLevelById(long id);

	List<Permission> getAllPermision();

	Permission getPermissionById(Long id);

}