package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.Register;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface RegisterMapper extends BaseMapper<Register> {
	
	@Select("SELECT department_id, department_name, doctor_id, doctor_name \r\n" + 
			"FROM v_api_register\r\n" + 
			"WHERE trade_result = '0'\r\n" + 
			"GROUP BY doctor_id")
	public List<JSONObject> selectDoctorList();
	
	public List<JSONObject> selectRegisterList(String name, String termId, String orderId, String hospOrderId, String transactionNo, String bankCardNo, String totalFee,
			String departmentId, String doctorId,
			String channelType, String payType, String cardType, String tradeResult,
			String datetype, String startDate, String endDate);

	public List<JSONObject> selectCountAndSumByDateAndChannel(String departmentId, String doctorId, String datetype, String startDate, String endDate);

	public List<JSONObject> selectCountAndSumByChannel(String departmentId, String doctorId, String datetype, String startDate, String endDate);
}
