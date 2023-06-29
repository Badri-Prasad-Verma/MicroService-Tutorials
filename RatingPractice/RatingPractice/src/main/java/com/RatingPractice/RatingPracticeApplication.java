package com.RatingPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingPracticeApplication.class, args);
	}

}
