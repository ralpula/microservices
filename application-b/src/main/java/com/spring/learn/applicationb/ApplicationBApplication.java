package com.spring.learn.applicationb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBApplication.class, args);
	}

}
