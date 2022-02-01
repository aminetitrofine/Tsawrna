package com.moroccanpixels.moroccanpixels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MoroccanpixelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoroccanpixelsApplication.class, args);
	}
}
