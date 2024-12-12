package com.springboot.CinemaSystem.service.impl;

import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.repository.*;
import com.springboot.CinemaSystem.service.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDaoImpl implements UserDao, UserDetailsService {
	private UserRepository userRepository;
	private AgentRepository agentRepository;
	private CustomerRepository customerRepository;
	private EmployeeRepository employeeRepository;
	private ManagerRepository managerRepository;
	private AdminRepository adminRepository;
	private LevelRepository levelRepository;
	private RoleRepository roleRepository;
	private PermisionRepository permisionRepository;

	@Autowired
	public UserDaoImpl(UserRepository userRepository, AgentRepository agentRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, ManagerRepository managerRepository, AdminRepository adminRepository, LevelRepository levelRepository, RoleRepository roleRepository, PermisionRepository permisionRepository, EntityManager entityManager) {
		this.userRepository = userRepository;
		this.agentRepository = agentRepository;
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
		this.managerRepository = managerRepository;
		this.adminRepository = adminRepository;
		this.levelRepository = levelRepository;
		this.roleRepository = roleRepository;
		this.permisionRepository = permisionRepository;
		this.entityManager = entityManager;
	}

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public User getUserByID(long userID) {
		return userRepository.findById(userID)
				.orElseThrow(() -> new NotFoundException("User not found with ID: " + userID));
	}

	@Override
	public User updateUser(User user) {
		if(!userRepository.existsById(user.getID())) {
			throw new NotFoundException("No found user:" +user.getID());
		}
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new DataProcessingException("Error updateUser: " + e.getMessage());
		}
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getCustomerById: " + id));
	}

	@Override
	public Customer addCustomer(Customer customer) {
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new DataProcessingException("Error addCustomer: " + e.getMessage());
		}
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
	public List<Customer> getAllCustomers() {
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException("Error getAllCustomers: " + e.getMessage());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Error findByUsername: " + username));
//		String roleName = user.getRole().getName();
//		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
		Set<GrantedAuthority> authorities = user.getRole().getPermission().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(
				username,
				user.getAccount().getPassword(),
				user.isStatus(),	// Trả về true nếu tài khoản được kích hoạt.
				true,	//	Trả về true nếu tài khoản chưa hết hạn.
				true,	//	Trả về true nếu thông tin đăng nhập chưa hết hạn.
				true,	//	Trả về true nếu tài khoản chưa bị khóa.
				authorities);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Error findByUsername: " + username));
	}

	@Override
	public List<Employee> getAllEmployee() {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public Employee addEmployee(Employee employee) {
		try {
			return employeeRepository.save(employee);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if(!employeeRepository.existsById(employee.getID())) {
			throw new NotFoundException("Not found employee: " + employee.getID());
		}
		try {
			return employeeRepository.save(employee);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Employee convertToEmployee(long id) {
		entityManager.createNativeQuery("UPDATE user SET user_type = 'EMPLOYEE' WHERE userid = :id")
				.setParameter("id", id).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		return employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Employee: " + id));
	}

	@Override
	@Transactional
	public Manager convertToManager(long id) {
		entityManager.createNativeQuery("UPDATE user SET user_type = 'MANAGER' WHERE userid = :id")
				.setParameter("id", id).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		return managerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Manager: " + id));
	}

	@Override
	@Transactional
	public Agent convertToAgent(long id) {
		entityManager.createNativeQuery("UPDATE user SET user_type = 'AGENT' WHERE userid = :id")
				.setParameter("id", id).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		return agentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Agent: " + id));
	}

	@Override
	@Transactional
	public Admin convertToAdmin(long id) {
		entityManager.createNativeQuery("UPDATE user SET user_type = 'ADMIN' WHERE userid = :id")
				.setParameter("id", id).executeUpdate();
		entityManager.flush();
		entityManager.clear();
		return adminRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Admin: " + id));
	}

	@Override
	public Admin addAdmin(Admin admin) {
		try {
			return adminRepository.save(admin);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Manager addManager(Manager manager) {
		try {
			return managerRepository.save(manager);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Agent addAgent(Agent agent) {
		try {
			return agentRepository.save(agent);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Employee: " + id));
	}

	@Override
	public Admin getAdminById(long id) {
		return adminRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found admin: " + id));
	}

	@Override
	public Manager getManagerById(long managerid) {
		return managerRepository.findById(managerid)
				.orElseThrow(() -> new NotFoundException("Not found manager: " + managerid));
	}

	@Override
	public Agent getAgentById(long id) {
		return agentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found Agent: " + id));
	}

	@Override
	public List<Role> getAllRole() {
		try {
			return roleRepository.findAll(); // Collect the stream into a list
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Role getRoleById(long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getRoleById: " + id));
	}

	@Override
	public Role addRole(Role role) {
		try {
			return roleRepository.save(role);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	public Role updateRole(Role role) {
		if(!roleRepository.existsById(role.getID())){
			throw new NotFoundException("Error getRoleById: " + role.getID());
		}
		try {
			return roleRepository.save(role);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		if(!roleRepository.existsById(role.getID())){
			throw new NotFoundException("Error getRoleById: " + role.getID());
		}
		try {
			role.getPermission().clear();
			Role role_new = this.updateRole(role);
			roleRepository.delete(role_new);
		} catch (Exception e) {
			throw new DataProcessingException(e.getMessage());
		}

	}

	@Override
	public Level getLevelById(long id) {
		return levelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getLevelById: " + id));
	}

	@Override
	public List<Permission> getAllPermision() {
		try {
			return permisionRepository.findAll();
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public Permission getPermissionById(Long id) {
		return permisionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Error getPermissionById: " + id));
	}

}