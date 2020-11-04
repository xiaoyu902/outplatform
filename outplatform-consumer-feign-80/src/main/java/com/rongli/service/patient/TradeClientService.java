package com.rongli.service.patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;

@FeignClient(value = "OUTPLATFORM-PROVIDER-API",fallbackFactory = TradeServiceFallback.class)
public interface TradeClientService {

	@RequestMapping("service/net")
	public ResultBody net();
	
	@RequestMapping("service/api")
	public String api(@RequestParam("serviceId") String serviceId,@RequestBody JSONObject body);
}
