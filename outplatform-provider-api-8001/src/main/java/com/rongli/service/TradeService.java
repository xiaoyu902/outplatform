package com.rongli.service;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.entities.params.ServiceResult;

public interface TradeService {

	public ResultBody net();
	
	public ServiceEntity getApiEntity(String serviceId);
	
	public ServiceResult doTrade(ServiceEntity  se,JSONObject recv);
}
