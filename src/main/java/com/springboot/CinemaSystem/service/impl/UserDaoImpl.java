package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.repository.UserRepository;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.AgentRepository;
import com.springboot.CinemaSystem.repository.CustomerRepository;
import com.springboot.CinemaSystem.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDaoImpl implements UserDao {
	private final UserRepository userRepository;
	private AgentRepository agentRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public UserDaoImpl(UserRepository userRepository, AgentRepository agentRepository, CustomerRepository customerRepository) {
		this.userRepository = userRepository;
		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getCustomerById: " + id));
	}

	@Override
	public void updateCustomer(Customer customer) {
		if(!customerRepository.existsById(customer.getID())){
			throw new NotFoundException("Error updateCustomer: " + customer.getID());
		}
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			throw new DataProcessingException("Error updateCustomer: " + e.getMessage());
		}
	}

	@Override
	public List<UserDto> getAllCustomers() {
		// Lấy tất cả người dùng có user_type là "user"
		try{
			return userRepository.findByUserType("user");
		}catch(Exception e){
			throw new DataProcessingException("Failed to retrieve customers: " + e.getMessage());
		}
	}

	@Override
	public User getUserByID(int userID) {
		return null;
	}

	@Override
	public boolean changePassword(String password) {
		return false;
	}

	@Override
	public boolean deleteAccount(int accountID) {
		return false;
	}

	@Override
	public User login(Account account) {
		return null;
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public boolean addUser(User user) {
		return false;
	}
}