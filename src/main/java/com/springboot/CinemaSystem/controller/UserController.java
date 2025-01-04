package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.service.*;
import com.springboot.CinemaSystem.util.JwtTokenUtil;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
    private EmailDao emailDao;

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
    @Transactional
    public boolean register(@RequestBody UserDto userDto) {
        if(userDao.getUserByUsername(userDto.getEmail()) != null) {
            return false;
        }
        String password = userDto.getPassword();
        System.out.println(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        Customer customer = Customer.toCustomer(userDto);
        customer.setStatus(false);
        customer.setRole(userDao.getRoleById(4));
        customer.setLevel(userDao.getLevelById(1));

        String to = customer.getEmail();
        String subject = "LAL Cinema - Mã xác minh tài khoản";
        String verificationCode = generateVerificationCode(); // Tạo mã xác minh
        String verificationLink = "http://localhost:3000/verifyaccount?username=" + to + "&token=" + verificationCode;
        String text = "<h3>Chào " + customer.getName() + ",</h3>"
                + "<p>Cảm ơn bạn đã đăng ký tài khoản tại LAL Cinema!</p>"
                + "<p>Vui lòng nhấn vào link dưới đây để kích hoạt tài khoản của bạn:</p>"
                + "<a href='" + verificationLink + "'>Xác nhận tài khoản</a>";
        emailDao.sendEmail(to, subject, text);

        customer.setVerificationCode(verificationCode);
        userDao.addCustomer(customer);
        return true;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @PostMapping("/public/verifyaccount")
    public boolean verifyAccount(@RequestBody Account account){
            User user = userDao.getUserByUsername(account.getUsername());
        if(user != null && user.getVerificationCode().equals(account.getPassword())){
            user.setStatus(true);
            user.setVerificationCode(null);
            userDao.updateUser(user);
            return true;
        }
        return false;
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
            Customer customer = userDao.getCustomerById(userDto.getId());
            userDto.setStatusEmployee(false);
            userDto.setCountGenre(customer.getGenre().size());
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

    @GetMapping("/public/forgotpassword/{email}")
    public String forgotPassword(@PathVariable("email") String email) {
        User user = userDao.getUserByUsername(email);
        if(user != null){
            String to = email;
            String subject = "LAL Cinema - Quên mật khẩu";
            String verificationCode = generateVerificationCode();
            String text = "Chúng tôi nhận được yêu cầu quên mật khẩu từ bạn. Mã xác thực của bạn là: " + verificationCode +
                    ".\nVui lòng sử dụng mã này để khôi phục mật khẩu.";
            emailDao.sendEmail(to, subject, text);
            return verificationCode;
        }
        return null;
    }

    @PutMapping("/public/changepassword")
    @Transactional
    public String changePasswordCustomer(@RequestBody UserDto userDto) {
        User user = userDao.getUserByUsername(userDto.getUsername());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setAccount(new Account(userDto.getUsername(), encodedPassword));
        User user_new = userDao.updateUser(user);
        return this.login(new Account(userDto.getUsername(), userDto.getPassword()));
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
            String imageUrl = fileStorageDao.updateFile(file, user.getImage(), "Image/User", "image");
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

    @PreAuthorize("hasAuthority('MANAGER_EMPLOYEE')")
    @GetMapping("/employee/{id}")
    public UserDto getEmployeeById(@PathVariable("id") long id) {
        User user = userDao.getUserByID(id);
        Employee employee = userDao.getEmployeeById(id);
        UserDto dto = UserDto.toEmployeeDto(employee);
        dto.setPosition(employee.getPosition());
        if(user.getRole().getID() == 1) {
            Admin admin = userDao.getAdminById(id);
            dto.setAccessLevel(admin.getAccessLevel());
        }
        else if(user.getRole().getID() == 2) {
            Manager manager = userDao.getManagerById(id);
            dto.setTheaterid(manager.getTheater().getID());
        }
        else if(user.getRole().getID() == 3) {
            Agent agent = userDao.getAgentById(id);
            dto.setManagerid(agent.getManager().getID());
        }
        return dto;
    }

    @GetMapping("/public/recommend/{customerID}")
    public List<MovieDto> recommendMovies(@PathVariable("customerID") Long customerID) {
        List<Genre> genres = movieDao.customerGenre(customerID);
        List<Long> genreIds = genres.stream()
                .map(Genre::getID)
                .collect(Collectors.toList());
        List<MovieDto> movies = movieDao.recommendMovies(genreIds);
        Collections.shuffle(movies);
        return movies.stream()
                .limit(6)
                .collect(Collectors.toList());
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
        employee.setPosition(userDto.getPosition());
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

    @PreAuthorize("hasAuthority('MANAGER_EMPLOYEE')")
    @PutMapping("/employee/update")
    @Transactional
    public UserDto updateEmployee(@RequestBody UserDto userDto) {
        if(userDto.getRoleid_old() == 1) {
            Admin admin = userDao.getAdminById(userDto.getId());
            admin.setAccessLevel(null);
            userDao.updateAdmin(admin);
        }
        else if(userDto.getRoleid_old() == 2) {
            System.out.println(userDto.getId());

            Manager manager = userDao.getManagerById(userDto.getId());
            System.out.println(userDto.getManagerid_new());
            if(userDto.getManagerid_new() > 0) {
                manager.setTheater(null);
                Manager manager_2 = userDao.getManagerById(userDto.getManagerid_new());
                for (Agent agent : manager.getAgents()) {
                    agent.setManager(manager_2);
                }
                manager_2.getAgents().addAll(manager.getAgents());
                manager.getAgents().clear();
                userDao.updateManager(manager);
                userDao.updateManager(manager_2);
            }
        }
        else if(userDto.getRoleid_old() == 3) {
            Agent agent = userDao.getAgentById(userDto.getId());
            agent.setManager(null);
            userDao.updateAgent(agent);
        }
        Employee employee = userDao.convertToEmployee(userDto.getId());
        employee.setPosition(userDto.getPosition());
        employee.setRole(userDao.getRoleById(userDto.getRoleid()));
        userDao.updateEmployee(employee);
        if(userDto.getRoleid() == 1) {
            Admin admin = userDao.convertToAdmin(employee.getID());
            admin.setAccessLevel(userDto.getAccessLevel());
            userDao.addAdmin(admin);
        }
        else if (userDto.getRoleid() == 2) {
            Manager manager = userDao.convertToManager(employee.getID());
            Theater theater = theaterDao.getTheaterByID(userDto.getTheaterid());
            manager.setTheater(theater);
            userDao.addManager(manager);
        }
        else if (userDto.getRoleid() == 3) {
            Agent agent = userDao.convertToAgent(employee.getID());
            Manager manager = userDao.getManagerById(userDto.getManagerid());
            agent.setManager(manager);
            userDao.addAgent(agent);
        }
        return UserDto.toEmployeeDto(employee);
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