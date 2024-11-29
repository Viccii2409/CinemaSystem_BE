package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityContext().disable() // Tắt CSRF bảo vệ nếu không cần thiết
                .authorizeRequests()
                .anyRequest().permitAll()  // Cho phép tất cả các yêu cầu mà không cần xác thực
                .and()
                .formLogin().disable()  // Tắt form đăng nhập nếu không cần
                .httpBasic().disable(); // Tắt http basic nếu không cần

        return http.build();
    }
}
