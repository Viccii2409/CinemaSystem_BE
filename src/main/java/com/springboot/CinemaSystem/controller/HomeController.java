package com.springboot.CinemaSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


        @GetMapping("/home")
        public String home() {
            return "home"; // Trả về trang home.html
        }

        @GetMapping("/admin")
        public String admin() {
            return "admin"; // Trả về trang admin.html
        }
    }

