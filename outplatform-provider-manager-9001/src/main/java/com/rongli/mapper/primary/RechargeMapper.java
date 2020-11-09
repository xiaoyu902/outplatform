package com.rongli.mapper.primary;

import org.apache.ibatis.annotations.CacheNamespace;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.params.Recharge;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface RechargeMapper extends BaseMapper<Recharge>{

}
