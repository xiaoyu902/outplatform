package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.Patient;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface PatientMapper extends BaseMapper<Patient> {

	@Select("SELECT * FROM v_api_create WHERE name LIKE #{name} AND term_id LIKE #{termId}")
	public List<JSONObject> selectListByNameAndTermId(@Param("name") String name, @Param("termId") String termId);

	
}
