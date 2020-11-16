package com.rongli.mapper;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface InputDicMapper {

	public JSONObject getPayType(@Param("data")String data);
	
	public JSONObject getCardType(@Param("data")String data);
	
	public JSONObject getCertType(@Param("data")String data);
	
	public JSONObject getChannel(@Param("data")String data);
	
	public JSONObject getCountry(@Param("data")String data);
	
	public JSONObject getFeeType(@Param("data")String data);
	
	public JSONObject getRechargeType(@Param("data")String data);
	
	public JSONObject getSex(@Param("data")String data);
	
	public JSONObject getTimeType(@Param("data")String data);
	
	public JSONObject getDocTitle(@Param("data")String data);
	
	public JSONObject getRegType(@Param("data")String data);
}
