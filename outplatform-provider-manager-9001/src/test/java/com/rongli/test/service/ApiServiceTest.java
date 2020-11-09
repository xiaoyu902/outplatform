package com.rongli.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.rongli.service.ApiService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //servlet环境，随机端口)
public class ApiServiceTest {

	@Autowired
	public ApiService apiService;
	
	@Test
	public void test1() {
		JSONObject obj = (JSONObject) apiService.selectPatientList(1, 10, "", "");
		System.out.println(obj.toJSONString());
	}
	
	
}
