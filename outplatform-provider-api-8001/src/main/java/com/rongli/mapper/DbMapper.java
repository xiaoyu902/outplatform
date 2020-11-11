package com.rongli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DbMapper{

	public int saveDB(@Param("tableName")String tableName,@Param("keys")List<String> keys,@Param("values")List<String> values);
}
