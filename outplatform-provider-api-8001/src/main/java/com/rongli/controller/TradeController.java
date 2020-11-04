package com.rongli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.entities.params.ServiceResult;
import com.rongli.service.TradeService;

@RestController
@RequestMapping("service")
public class TradeController {
	
	@Autowired
	private TradeService tradeService;

	@RequestMapping("net")
	public ResultBody net() {
		return tradeService.net();
	}
	
	@RequestMapping("api")
	public String create(String serviceId,@RequestBody JSONObject body) {
		ServiceEntity se=tradeService.getApiEntity(serviceId);
		
		ServiceResult sr=tradeService.doTrade(se, body);
		
		return sr.getToThirdData();
	}
}
