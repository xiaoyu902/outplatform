package com.rongli.config.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.rongli.common.util.RedisUtil;
import com.rongli.common.util.SerialNumberUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class LoadCpuSerial {
	
	public String redis_key="cpuid";

	@Autowired
	RedisUtil redisUtil;
	
	@Async
	public void start() {
		String cpuid=SerialNumberUtil.getCPUSerial();
		boolean result=redisUtil.set(redis_key, cpuid);
		if(result) 
			log.info("缓存cpuid={}成功",cpuid);
		else
			log.error("缓存cpuid={}失败",cpuid);
	}
}
