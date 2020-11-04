package com.rongli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//屏蔽公共api中的jdbc依赖包
@EnableEurekaClient
public class GateWayMain9527 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(GateWayMain9527.class, args);
	}

}
