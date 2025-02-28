package com.gymex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gymex")
public class GymexApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymexApplication.class, args);
	}

}
