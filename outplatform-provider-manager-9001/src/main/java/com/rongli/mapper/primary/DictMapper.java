package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface DictMapper extends BaseMapper {
	
	@Select("SELECT * FROM t_dic_channel")
	public List<JSONObject> selectChannelList();
}
