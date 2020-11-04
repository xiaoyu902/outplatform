package com.rongli.service.imp;

import org.springframework.stereotype.Component;

import com.rongli.common.utils.SpringUtil;
import com.rongli.mapper.OutputDicMapper;
import com.rongli.service.DicHandleService;

@Component
public class OutputDicHandleServiceImp implements DicHandleService{
	
	@Override
	public String getCardType(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getCardType(data);
	}

	@Override
	public String getCertType(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getCertType(data);
	}

	@Override
	public String getChannel(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getChannel(data);
	}

	@Override
	public String getCountry(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getCountry(data);
	}

	@Override
	public String getFeetype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getFeeType(data);
	}

	@Override
	public String getPaytype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getPayType(data);
	}

	@Override
	public String getRechargetype(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getRechargeType(data);
	}

	@Override
	public String getSex(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getSex(data);
	}

	@Override
	public String getTime(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getTimeType(data);
	}

	@Override
	public String getDocTitle(String data) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(OutputDicMapper.class).getDocTitle(data);
	}
	


}
