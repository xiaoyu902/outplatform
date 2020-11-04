package com.rongli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;
import com.rongli.service.patient.TradeClientService;

@RestController
@RequestMapping("his/service")
public class TradeConsumerController {

	@Autowired
	TradeClientService tradeClientService;
	
	@RequestMapping("net")
	public ResultBody net() {
		return tradeClientService.net();
	}
	
	@RequestMapping("api")
	public String api(@RequestParam("serviceId") String serviceId,@RequestBody JSONObject body) {
		String str=tradeClientService.api(serviceId,body);
		return str;
	}
}
