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
	public JSONObject checkChannel(String type) {
		// TODO Auto-generated method stub
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getChannel(type);
		return obj;
			
	}

	@Override
	public JSONObject checkCertType(String data) {
		// TODO Auto-generated method stub
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getCertType(data);
		return obj;
	}


	@Override
	public JSONObject checkPaytype(String data) {
		// TODO Auto-generated method stub
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getPayType(data);
		return obj;
	}

	@Override
	public JSONObject checkSex(String data) {
		// TODO Auto-generated method stub
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getSex(data);
		return obj;
	}


}
