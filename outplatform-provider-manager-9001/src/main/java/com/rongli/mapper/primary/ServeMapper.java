package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.ServiceEntity;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface ServeMapper extends BaseMapper<ServiceEntity> {

	@Select("select table_name from information_schema.tables \r\n" + 
			"where  table_name like \"t_api_%\" \r\n" + 
			"and table_name != \"t_api_refund\";")
	public List<String> findTableList();
	
	@Select("select COLUMN_NAME as name,column_comment as comment  from information_schema.COLUMNS where table_name = #{tableName};")
	public List<JSONObject> findColumnListByTable(String tableName);
	
}
