package com.rongli.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.rongli.Application;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.mapper.primary.BaseMapper;
import com.rongli.mapper.primary.ServeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //servlet环境，随机端口)
public class BaseMapperTest {

	@Autowired
	private ServeMapper serveMapper;
	
	@Test
	public void test() {
		List<ServiceEntity> list = serveMapper.selectList(null);
		for (ServiceEntity serviceEntity : list) {
			System.out.println(serviceEntity.toString());
		}
	}
	
	@Test
	public void testFindTableList() {
		List<String> list = serveMapper.findTableList();
		for (String tableName : list) {
			System.out.println(tableName);
		}
	}
	
	@Test
	public void testFindColumnListByTable() {
		List<JSONObject> list = serveMapper.findColumnListByTable("t_api_pay");
		for (JSONObject obj : list) {
			System.out.println(obj.getString("name") + ":" + obj.getString("comment"));
		}
	}
}
