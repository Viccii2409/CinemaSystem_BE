package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.filter.JwtTokenUtil;
import com.springboot.CinemaSystem.service.FileStorageService;
import com.springboot.CinemaSystem.service.MovieDao;
import com.springboot.CinemaSystem.service.UserDao;
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
    private MovieDao movieDao;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    PasswordEncoder passwordEncoder;
    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    public UserController(UserDao userDao, MovieDao movieDao,AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.movieDao= movieDao;
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
    public  String register(@RequestBody UserRegisterDto userRegisterDto) {
        String password = userRegisterDto.getPassword();
        System.out.println(userRegisterDto);
        String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encodedPassword);
        Customer customer = Customer.toCustomer(userRegisterDto);
        customer.setStatus(true);
        customer.setRole(userDao.getRoleById(4));
        customer.setLevel(userDao.getLevelById(1));
        userDao.addCustomer(customer);
        return this.login(new Account(userRegisterDto.getEmail(), password));
    }

    @GetMapping("/public/verify")
    public UserVerifyDto verifyUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getUserByUsername(userDetails.getUsername());
        return user.toUserVerifyDto();

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


    @GetMapping("/public/employee")
    public List<EmployeeDto> getEmployee() {
        return userDao.getAllEmployee().stream()
                .map(entry -> entry.toEmployeeDto())
                .collect(Collectors.toList());
    }

    @GetMapping("/public/employee/{id}")
    public boolean addEmployee(@PathVariable("id") long id) {
        System.out.println(id);
//        Customer customer = userDao.getCustomerById(id);
//        Employee employee = Employee.convertCustomerToEmployee(customer);
//        userDao.updateEmployee(employee);
        return true;
    }

    @PreAuthorize("hasAnyAuthority('BOOKING', 'VIEW_CUSTOMER_INFOR')")
    @GetMapping("/customer/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") long id) {
        Customer customer = userDao.getCustomerById(id);
        System.out.println(customer.getBooking().size());
        return customer.toCustomerDto();
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
            String imageUrl = fileStorageService.updateFile(file, user.getImage());
            user.setImage(imageUrl);
            userDao.updateUser(user);
            return imageUrl;
        }
        return null;
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @GetMapping("/role")
    public List<RoleDto> getAllRole() {
        List<RoleDto> roles = userDao.getAllRole().stream()
                .map(entry -> entry.toRoleDto()).collect(Collectors.toList());
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
    public RoleDto addRole(@RequestBody RoleRequestDto roleRequestDto) {
        Role role = new Role();
        role.setName(roleRequestDto.getName());
        for(Long l : roleRequestDto.getPermission()){
            Permission permission = userDao.getPermissionById(l);
            role.getPermission().add(permission);
        }
        Role role_new = userDao.addRole(role);
        return role_new.toRoleDto();
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @PutMapping("/role/update")
    public RoleDto updateRole(@RequestBody RoleRequestDto roleRequestDto) {
        Role role = userDao.getRoleById(roleRequestDto.getId());
        role.setName(roleRequestDto.getName());
        role.getPermission().clear();
        for(Long l : roleRequestDto.getPermission()){
            Permission permission = userDao.getPermissionById(l);
            role.getPermission().add(permission);
        }
        Role role_new = userDao.updateRole(role);
        return role_new.toRoleDto();
    }

    @PreAuthorize("hasAuthority('MANAGER_ROLE')")
    @DeleteMapping("/role/{id}/delete")
    public boolean deleteRole(@PathVariable("id") long id) {
        Role role = userDao.getRoleById(id);
        userDao.deleteRole(role);
        return true;
    }
    @GetMapping("/public/recommend/{customerID}")
    public List<MovieDto> recommendMovies(@PathVariable("customerID") Long customerID) {
        List<Genre> genres = movieDao.customerGenre(customerID);
        List<Long> genreIds = genres.stream()
                .map(Genre::getID)
                .collect(Collectors.toList());
        return  movieDao.recommendMovies(genreIds);
    }

//    @PreAuthorize("hasAuthority('MANAGER_CUSTOMER')")
//    @GetMapping("/inforaccount/{id}")
//    public CustomerDto getCustomerInfor(@PathVariable("id") long id) {
//        Customer customer = userDao.getCustomerById(id);
//        return customer.toCustomerDto();
//    }
@PreAuthorize("hasAuthority('MANAGER_CUSTOMER')")
    @GetMapping("/all-customers")
    public List<CustomerDto> getAllCustomers() {
        return userDao.getAllCustomers();

    }

//    public static class LoginResponse {
//        private String token;
//
//        public LoginResponse(String token) {
//            this.token = token;
//        }
//
//        // Getter
//        public String getToken() {
//            return token;
//        }
//    }

//    @PostMapping("/public/pass/{p}")
//    public String addPass(@PathVariable("p") String p) {
//        return passwordEncoder.encode(p);
//    }

//    @GetMapping("/public/role")
//    public List<RoleDto> getRoleDto() {
//        List<RoleDto> roles = userDao.getAllRole().stream()
//                .map(entry -> new RoleDto(
//                        entry.getID(),
//                        entry.getName(),
//                        entry.getPermission().stream() // Ensure getter name matches entity
//                                .map(permission -> new PermissionDto(permission.getID(), permission.getName()))
//                                .collect(Collectors.toList())
//                ))
//                .collect(Collectors.toList());
//        return roles;
//    }

//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; // Trả về trang đăng nhập (login.html)
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Account account) {
//        // Kiểm tra thông tin đăng nhập
//        Account foundAccount = accountDao.findAccountByUsername(account.getEmail());
//        if (foundAccount != null && foundAccount.getPassword().equals(account.getPassword())) {
//            // Trả về thông tin user và loại tài khoản (user_type)
//            return ResponseEntity.ok().body(new LoginResponse( foundAccount.getID(),foundAccount.getUser().getName(), foundAccount.getUser().getAddress(),
//                            foundAccount.getUser().getDob(),foundAccount.getEmail(),foundAccount.getPassword(),foundAccount.getUser().getGender(),
//                    foundAccount.getUser().getPhone(),
//                    foundAccount.getUser().getPrivileges()
//                   ));
//
//        } else {
//            // Trả về lỗi nếu đăng nhập không thành công
//            return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng");
//        }
//    }

//    @PostMapping("/login")
//    public UserDto login(@RequestBody Account account) {
//        User user = userDao.loadUserByAccount(account);
//    }

//    @PutMapping("/update")
//    public ResponseEntity<String> updateProfile(@RequestBody User request) {
//        try {
//            userDao.updateUser(request);
//            return ResponseEntity.ok("Thông tin cá nhân được cập nhật thành công!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cập nhật thất bại: " + e.getMessage());
//        }
//    }

}