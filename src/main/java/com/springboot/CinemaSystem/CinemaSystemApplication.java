package com.springboot.CinemaSystem;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaSystemApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dandjf6ke",
				"api_key", "238846359829248",
				"api_secret", "CVLV6CP20iuQJ34bB1-NJDJXFvM",
				"secure", true
		));
	}

}
