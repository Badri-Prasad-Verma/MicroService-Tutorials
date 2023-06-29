package com.HotelPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelPracticeApplication.class, args);
	}

}
