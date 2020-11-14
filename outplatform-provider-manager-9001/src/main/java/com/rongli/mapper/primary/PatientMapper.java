package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.Patient;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface PatientMapper extends BaseMapper<Patient> {
	
	public List<JSONObject> selectPatientList(String name, String termId, String patientId, String cardType, String tradeResult, String startDate, String endDate);

}
