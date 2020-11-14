package com.rongli.test.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.rongli.mapper.primary.RegisterMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //servlet环境，随机端口)
public class RegisterMapperTest {

	@Autowired
	private RegisterMapper registerMapper;
	
	@Test
	public void test1() {
		List<JSONObject> doctorList = registerMapper.selectDoctorList();
		for (JSONObject doctor : doctorList) {
			System.out.println(doctor.toJSONString());
		}
	}
	
	@Test
	public void test2() {
		List<JSONObject> list = registerMapper.selectCountAndSumByDateAndChannel(null, null, "y", "2020-01-01 00:00:00", "2020-12-12 00:00:00");
		for (JSONObject obj : list) {
			System.out.println(obj.toJSONString());
		}
	}
}
