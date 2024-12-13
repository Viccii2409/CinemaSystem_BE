package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.util.JwtTokenUtil;
import com.springboot.CinemaSystem.service.FileStorageDao;
import com.springboot.CinemaSystem.service.MovieDao;
import com.springboot.CinemaSystem.service.TheaterDao;
import com.springboot.CinemaSystem.service.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserDao userDao;
    private TheaterDao theaterDao;
    private MovieDao movieDao;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    PasswordEncoder passwordEncoder;
    private FileStorageDao fileStorageDao;

    @Autowired
    public UserController(UserDao userDao, TheaterDao theaterDao, MovieDao movieDao, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, FileStorageDao fileStorageDao) {
        this.userDao = userDao;
        this.theaterDao = theaterDao;
        this.movieDao = movieDao;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.fileStorageDao = fileStorageDao;
    }

    @PostMapping("/public/login")
    public String login(@RequestBody Account account) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            account.getUsername(),
                            account.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Tạo JWT token
            String token = jwtTokenUtil.generateToken(authentication.getName());

            return token;
        } catch (Exception e) {
//            throw new DataProcessingException(e.getMessage());
            return null;
        }
    }

    @PostMapping("/public/register")
    public  String register(@RequestBody UserDto userDto) {
        String password = userDto.getPassword();
        System.out.println(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        Customer customer = Customer.toCustomer(userDto);
        customer.setStatus(true);
        customer.setRole(userDao.getRoleById(4));
        customer.setLevel(userDao.getLevelById(1));
        userDao.addCustomer(customer);
        return this.login(new Account(userDto.getEmail(), password));
    }

    @GetMapping("/public/verify")
    public UserDto verifyUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserByUsername(userDetails.getUsername());
        UserDto userDto = UserDto.toUserVerifyDto(user);
        if(userDto.getRole().getID() != 4) {
            Employee employee = userDao.getEmployeeById(userDto.getId());
            userDto.setStatusEmployee(employee.getStatusEmployee());
        }
        else {
            userDto.setStatusEmployee(false);
        }
        return userDto;

    }

    @PostMapping("/public/check")
    public boolean checkUsername(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        return userDao.getUserByUsername(username) != null;
    }


    @GetMapping("/public/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userDao.getUserByID(id);
    }


    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        User user = userDao.getUserByID(id);
        System.out.println(user.getRole().getName());
        if(user.getRole().getID() == 1) {
            Admin admin = userDao.getAdminById(id);
            return UserDto.convertAdminToUserDto(admin);
        }
        else if(user.getRole().getID() == 2) {
            Manager manager = userDao.getManagerById(id);
            return UserDto.convertManagertoUserDto(manager);
        }
        else if(user.getRole().getID() == 3) {
            Agent agent = userDao.getAgentById(id);
            return UserDto.convertAgenttoUserDto(agent);
        }
        else if(user.getRole().getID() == 4) {
            Customer customer = userDao.getCustomerById(id);
            return UserDto.convertCustomertoUserDto(customer);
        }
        else {
            Employee employee = userDao.getEmployeeById(id);
            return UserDto.convertEmployeeToUserDto(employee);
        }
    }

    @PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PutMapping("/changepassword")
    public boolean changePassword(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String passwordOld = requestBody.get("passwordOld");
        String passwordNew = requestBody.get("passwordNew");
        System.out.println(username + " " + passwordOld + " " + passwordNew);
        String token = this.login(new Account(username, passwordOld));
        if(token != null){
            User user = userDao.getUserByUsername(username);
            String encodedPassword = passwordEncoder.encode(passwordNew);
            user.getAccount().setPassword(encodedPassword);
            userDao.updateUser(user);
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PutMapping("/update")
    public boolean updateUser(@RequestBody UserDto userDto) {
        User user = userDao.getUserByID(userDto.getId());
        user.setName(userDto.getName());
        System.out.println(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());
        user.setDob(userDto.getDob());
        user.setAddress(userDto.getAddress());
        userDao.updateUser(user);
        return true;
    }

    @PreAuthorize("hasAuthority('VIEW_CUSTOMER_INFOR')")
    @PutMapping("/image/update")
    public String updateImage(@RequestParam(value = "id") long id, @RequestParam(value = "file") MultipartFile file) {
        User user = userDao.getUserByID(id);
        if(user != null) {
            String imageUrl = fileStorageDao.updateFile(file, user.getImage(), "Image/User");
            user.setImage(imageUrl);
            userDao.updateUser(user);
            return imageUrl;
        }
        return null;
    }

    @PreAuthorize("hasAnyAuthority('MANAGER_ROLE', 'MANAGER_EMPLOYEE')")
    @GetMapping("/role")
    public List<RoleDto> getAllRole() {
        List<RoleDto> roles = userDao.getAllRole().stream()
                .map(entry -> RoleDto.toRoleDto(entry)).collect(Collectors.toList());
        return roles;
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @GetMapping("/permission")
    public List<PermissionDto> getAllPermission() {
        List<PermissionDto> permissions = userDao.getAllPermision().stream()
                .map(entry -> new PermissionDto(
                        entry.getID(),
                        entry.getName()
                ))
                .collect(Collectors.toList());
        return permissions;
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @PostMapping("/role/add")
    public RoleDto addRole(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        for(Long l : dto.getPermission()){
            Permission permission = userDao.getPermissionById(l);
            role.getPermission().add(permission);
        }
        Role role_new = userDao.addRole(role);
        return RoleDto.toRoleDto(role_new);
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @PutMapping("/role/update")
    public RoleDto updateRole(@RequestBody RoleDto dto) {
        Role role = userDao.getRoleById(dto.getID());
        role.setName(dto.getName());
        role.getPermission().clear();
        for(Long l : dto.getPermission()){
            Permission permission = userDao.getPermissionById(l);
            role.getPermission().add(permission);
        }
        Role role_new = userDao.updateRole(role);
        return RoleDto.toRoleDto(role_new);
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @DeleteMapping("/role/{id}/delete")
    public boolean deleteRole(@PathVariable("id") long id) {
        Role role = userDao.getRoleById(id);
        userDao.deleteRole(role);
        return true;
    }

    @PreAuthorize("hasAuthority('MANAGER_EMPLOYEE')")
    @GetMapping("/employee")
    public List<UserDto> getEmployee() {
        return userDao.getAllEmployee().stream()
                .map(entry -> UserDto.toEmployeeDto(entry))
                .collect(Collectors.toList());
    }

    @GetMapping("/public/recommend/{customerID}")
    public List<MovieDto> recommendMovies(@PathVariable("customerID") Long customerID) {
        List<Genre> genres = movieDao.customerGenre(customerID);
        List<Long> genreIds = genres.stream()
                .map(Genre::getID)
                .collect(Collectors.toList());
        return  movieDao.recommendMovies(genreIds);
    }


    @PreAuthorize("hasAuthority('MANAGER_EMPLOYEE')")
    @PostMapping("/employee/check")
    public UserDto checkEmployee(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        User user = userDao.getUserByUsername(username);
        if(user != null) {
            return UserDto.toUserCheck(user);
        }
        return null;
    }


    @PreAuthorize("hasAuthority('MANAGER_EMPLOYEE')")
    @PostMapping("/employee/add")
    @Transactional
    public UserDto addEmployee(@RequestBody UserDto userDto) {
        Employee employee;
        long id;
        if(userDto.getId() != null) {
            id = userDto.getId();
            employee = userDao.convertToEmployee(id);
        }
        else {
            employee = new Employee();
            String encodePassword = passwordEncoder.encode(userDto.getPassword());
            Account account = new Account(userDto.getUsername(), encodePassword);
            employee.setAccount(account);
        }
        employee.setName(userDto.getName());
        employee.setEmail(userDto.getEmail());
        employee.setDob(userDto.getDob());
        employee.setPhone(userDto.getPhone());
        employee.setAddress(userDto.getAddress());
        Role role = userDao.getRoleById(userDto.getRoleid());
        employee.setRole(role);
        employee.setStatusEmployee(true);
        Employee employee_new = userDao.addEmployee(employee);
        System.out.println(role.getName());
        if(role.getName().equals("ADMIN")) {
            Admin admin = userDao.convertToAdmin(employee_new.getID());
            admin.setAccessLevel(userDto.getAccessLevel());
            Admin admin_new = userDao.addAdmin(admin);
        }
        else if (role.getName().equals("MANAGER")) {
            Manager manager = userDao.convertToManager(employee_new.getID());
            Theater theater = theaterDao.getTheaterByID(userDto.getTheaterid());
            manager.setTheater(theater);
            Manager manager_new = userDao.addManager(manager);
        }
        else if (role.getName().equals("AGENT")) {
            Agent agent = userDao.convertToAgent(employee_new.getID());
            Manager manager = userDao.getManagerById(userDto.getManagerid());
            agent.setManager(manager);
            Agent agent_new = userDao.addAgent(agent);
        }
        return UserDto.toEmployeeDto(employee_new);
    }

    @PutMapping("/employee/{id}/updatestatus")
    public boolean updateStatusEmployee(@PathVariable("id") long id) {
        Employee employee = userDao.getEmployeeById(id);
        employee.setStatusEmployee(!employee.getStatusEmployee());
        Employee employee_new = userDao.updateEmployee(employee);
        return employee_new.getStatusEmployee();
    }

    @PreAuthorize("hasAuthority('MANAGER_CUSTOMER')")
    @GetMapping("/customer")
    public List<UserDto> getAllCustomer() {
        return userDao.getAllCustomers().stream()
                .map(entry -> UserDto.convertCustomertoUserDto(entry))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('MANAGER_CUSTOMER')")
    @PutMapping("/{id}/updatestatus")
    public boolean updateStatusUser(@PathVariable("id") long id) {
        User user = userDao.getUserByID(id);
        user.setStatus(!user.isStatus());
        User user_new = userDao.updateUser(user);
        return user_new.isStatus();
    }

    @PostMapping("/customer/genre/add")
    @Transactional
    public void addGenreFauvorite(@RequestBody GenreRequestDto genreRequestDto) {
        Customer customer = userDao.getCustomerById(genreRequestDto.getCustomerid());
        if(customer != null) {
            customer.getGenre().clear();
            for(Integer genreid : genreRequestDto.getGenres()) {
                Genre genre = movieDao.getGenreByID(genreid);
                customer.getGenre().add(genre);
            }
            userDao.updateCustomer(customer);
        }
    }


}