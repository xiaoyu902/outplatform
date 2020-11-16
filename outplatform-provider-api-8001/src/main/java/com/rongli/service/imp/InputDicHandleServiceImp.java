package com.rongli.service.imp;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.rongli.common.utils.SpringUtil;
import com.rongli.entities.CardType;
import com.rongli.mapper.InputDicMapper;
import com.rongli.service.DicHandleService;

@Component
public class InputDicHandleServiceImp implements DicHandleService{
	

	@Override
	public String getCardType(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getCardType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getCertType(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getCertType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getChannel(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getChannel(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getCountry(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getCountry(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getFeetype(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getFeeType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getPaytype(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getPayType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getRechargetype(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getRechargeType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getSex(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getSex(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getTime(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getTimeType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getDocTitle(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getDocTitle(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

	@Override
	public String getRegtype(String data) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(data))
			return "";
		JSONObject obj=SpringUtil.getBean(InputDicMapper.class).getRegType(data);
		if(obj==null) {
			return null;
		}else {
			return obj.getString("his_type");
		}
	}

}
