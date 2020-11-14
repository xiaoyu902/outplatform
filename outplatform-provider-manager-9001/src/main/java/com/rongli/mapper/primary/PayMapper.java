package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.PayEntity;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface PayMapper extends BaseMapper<PayEntity> {

	public List<JSONObject> selectPayList(String name, String termId, String orderId, String billId, String transactionNo, String bankCardNo, String totalFee,
			String channelType, String payType, String cardType, String tradeResult,
			String datetype, String startDate, String endDate);
	
	public List<JSONObject> selectCountAndSumByDateAndChannel(String datetype, String startDate, String endDate);
	
	public List<JSONObject> selectCountAndSumByChannel(String datetype, String startDate, String endDate);
	
}
