package com.rongli.service.patient;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;

import feign.hystrix.FallbackFactory;

@Component
public class TradeServiceFallback implements FallbackFactory<TradeClientService>{

	@Override
	public TradeClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new TradeClientService() {
			
			@Override
			public ResultBody net() {
				// TODO Auto-generated method stub
				return ResultBody.error("连接不上服务提供者!"+cause.getMessage());
			}

			@Override
			public String api(String serviceId, JSONObject body) {
				// TODO Auto-generated method stub
				return ResultBody.error("服务降级，交易异常!"+cause.getMessage()).toString();
			}
		};
	}

}
