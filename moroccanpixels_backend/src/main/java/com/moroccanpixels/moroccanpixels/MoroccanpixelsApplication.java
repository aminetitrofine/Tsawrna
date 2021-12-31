package com.moroccanpixels.moroccanpixels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MoroccanpixelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoroccanpixelsApplication.class, args);
	}

	@GetMapping
	public static String hello(){
		return "Hello world";
	}
}
