package com.rongli.mapper;

import org.apache.ibatis.annotations.Param;

public interface InputDicMapper {

	public String getPayType(@Param("data")String data);
	
	public String getCardType(@Param("data")String data);
	
	public String getCertType(@Param("data")String data);
	
	public String getChannel(@Param("data")String data);
	
	public String getCountry(@Param("data")String data);
	
	public String getFeeType(@Param("data")String data);
	
	public String getRechargeType(@Param("data")String data);
	
	public String getSex(@Param("data")String data);
	
	public String getTimeType(@Param("data")String data);
	
	public String getDocTitle(@Param("data")String data);
	
	public String getRegType(@Param("data")String data);
}
