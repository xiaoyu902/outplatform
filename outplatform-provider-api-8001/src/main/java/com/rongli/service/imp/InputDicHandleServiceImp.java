package com.rongli.service.imp;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.rongli.common.utils.SpringUtil;
import com.rongli.exception.BaseException;
import com.rongli.mapper.InputDicMapper;
import com.rongli.service.DicHandleService;

@Component
public class InputDicHandleServiceImp implements DicHandleService{
	

	@Override
	public String getCardType(String data) {
		// TODO Auto-generated method stub
		String cardtype=SpringUtil.getBean(InputDicMapper.class).getCardType(data);
		return cardtype;
	}

	@Override
	public String getCertType(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getCertType(data);
	}

	@Override
	public String getChannel(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getChannel(data);
	}

	@Override
	public String getCountry(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getCountry(data);
	}

	@Override
	public String getFeetype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getFeeType(data);
	}

	@Override
	public String getPaytype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getPayType(data);
	}

	@Override
	public String getRechargetype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getRechargeType(data);
	}

	@Override
	public String getSex(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getSex(data);
	}

	@Override
	public String getTime(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getTimeType(data);
	}

	@Override
	public String getDocTitle(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(InputDicMapper.class).getDocTitle(data);
	}

}
