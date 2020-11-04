package com.rongli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableCircuitBreaker
@MapperScan("com.rongli.mapper")
public class ProviderPatient8001_App {

	public static void main(String[] args) {
		SpringApplication.run(ProviderPatient8001_App.class, args);
	}
}
