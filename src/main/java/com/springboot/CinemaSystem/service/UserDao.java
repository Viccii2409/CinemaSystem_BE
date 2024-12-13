package com.springboot.CinemaSystem.service;


import com.springboot.CinemaSystem.entity.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
	public User getUserByID(long userID);
	public User updateUser(User user);
	public Customer getCustomerById(long id);
	public Customer addCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public UserDetails loadUserByUsername(String username);
	public User getUserByUsername(String username);

	List<Employee> getAllEmployee();
	Employee addEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	Employee convertToEmployee(long id);
	Manager convertToManager(long id);
	Agent convertToAgent(long id);
	Admin convertToAdmin(long id);
	Admin addAdmin(Admin admin);
	Manager addManager(Manager manager);
	Agent addAgent(Agent agent);
	Employee getEmployeeById(long id);
	Admin getAdminById(long id);
	Manager getManagerById(long managerid);
	Agent getAgentById(long id);


	public List<Role> getAllRole();
	public Role getRoleById(long id);
	Role addRole(Role role);
	Role updateRole(Role role);
	void deleteRole(Role role);

	public Level getLevelById(long id);

	List<Permission> getAllPermision();
	Permission getPermissionById(Long id);
}