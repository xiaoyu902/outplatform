package com.rongli.service.imp;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.rongli.common.utils.SpringUtil;
import com.rongli.exception.BaseException;
import com.rongli.mapper.InputDicMapper;
import com.rongli.service.ParamCheckService;

@Component
public class ParamCheckServiceImp implements ParamCheckService{

	@Override
	public JSONObject checkChannelType(String type) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(type)) 
			throw new BaseException("渠道类型type为空");
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getChannel(type);
		return obj;
			
	}

}
