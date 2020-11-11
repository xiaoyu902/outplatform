package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.Recharge;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface RechargeMapper extends BaseMapper<Recharge>{
	
	public List<JSONObject> selectRechargeList(String name, String termId, String orderId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate);
}
