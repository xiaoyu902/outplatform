package com.rongli.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.rongli.mapper.primary.PatientMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //servlet环境，随机端口)
public class PatientMapperTest {

	@Autowired
	private PatientMapper patientMapper;
	
	@Test
	public void test1() {
		List<JSONObject> patientList = patientMapper.selectListByNameAndTermId("%测%", "%%");
		System.out.println(patientList.get(0).toJSONString());
	}
}
