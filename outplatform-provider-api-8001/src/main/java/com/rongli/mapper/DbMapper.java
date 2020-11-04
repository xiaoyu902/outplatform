package com.rongli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.entities.Patient;

public interface DbMapper{

	public int saveDB(@Param("tableName")String tableName,@Param("keys")List<String> keys,@Param("values")List<String> values);
}
