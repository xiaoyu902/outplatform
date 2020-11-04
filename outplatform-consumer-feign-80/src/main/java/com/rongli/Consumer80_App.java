package com.rongli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Consumer80_App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Consumer80_App.class, args);
	}

}
