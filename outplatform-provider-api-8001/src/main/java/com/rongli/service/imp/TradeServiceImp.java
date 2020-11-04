package com.rongli.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rongli.common.utils.SpringUtil;
import com.rongli.common.utils.StringUtil;
import com.rongli.driver.HospTradeDriver;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.entities.params.ServiceResult;
import com.rongli.exception.BaseException;
import com.rongli.mapper.ServiceApiMapper;
import com.rongli.service.DataHandleService;
import com.rongli.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeServiceImp implements TradeService{

	@Value("${org.driver-path}")
	private String driverPath;
	
	
	@Autowired
	private ServiceApiMapper serviceApiMapper;
	

	@Override
	public ResultBody net() {
		// TODO Auto-generated method stub
		return ResultBody.success("已连接");
	}

	@Override
	public ServiceEntity getApiEntity(String serviceId) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(serviceId))
			throw new BaseException("服务号为空");
		
		ServiceEntity se=serviceApiMapper.getServiceEntity(serviceId);
		if(se==null)
			throw new BaseException("未知的服务号");
		
		return se;
	}

	@Override
	public ServiceResult doTrade(ServiceEntity se, JSONObject recv) {
		// TODO Auto-generated method stub
		
		HospTradeDriver hd= null;
		try {
			hd= (HospTradeDriver) SpringUtil.getBean(StringUtil.getDriverName(driverPath), Class.forName(driverPath));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new BaseException("不合规范的实例化驱动类:"+driverPath);
		}
		
		ServiceResult sr=hd.doTrade(se, recv);
		return sr;
	}

}
