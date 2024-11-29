package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.Account;
import com.springboot.CinemaSystem.entity.Customer;
import com.springboot.CinemaSystem.entity.User;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.AccountRepository;
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
	private UserRepository userRepository;
	private AgentRepository agentRepository;
	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;

	@Autowired
	public UserDaoImpl(UserRepository userRepository, AgentRepository agentRepository, CustomerRepository customerRepository, AccountRepository accountRepository) {
		this.userRepository = userRepository;
		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
	}


	@Override
	public User getUserByID(long userID) {
		return userRepository.findById(userID)
				.orElseThrow(() -> new NotFoundException("Theater not found with ID: " + userID));
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
			return userRepository.findByUserType("User");
		}catch(Exception e){
			throw new DataProcessingException("Failed to retrieve customers: " + e.getMessage());
		}
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
		public void updateUser(User request) throws Exception {
			// Tìm User từ database
			User user = userRepository.findById(request.getID())
					.orElseThrow(() -> new Exception("User không tồn tại"));

			// Cập nhật thông tin User
			user.setName(request.getName());
			user.setPhone(request.getPhone());
			user.setGender(request.getGender());
			user.setDob(request.getDob());
			user.setAddress(request.getAddress());

			// Nếu có password mới, cập nhật vào Account
				Account account = user.getAccount();
				if (account != null) {
					account.setPassword(account.getPassword()); // Lưu trực tiếp mật khẩu
					accountRepository.save(account); // Lưu account
			}

			// Lưu thông tin User
			userRepository.save(user);
		}

	@Override
	public boolean addUser(User user) {
		return false;
	}
}