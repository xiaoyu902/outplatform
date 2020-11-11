package com.rongli.test.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.rongli.mapper.primary.PayMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //servlet环境，随机端口)
public class PayMapperTest {

	@Autowired
	private PayMapper payMapper;
	
	@Test
	public void test1() {
		List<JSONObject> payList = payMapper.selectCountAndSumByDateAndChannel("y", "2020-01-01 00:00:00", "2020-11-11 23:59:59");
		System.out.println(payList.get(0).toJSONString());
	}
	
}
