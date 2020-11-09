package com.rongli.mapper.primary;

import java.util.Arrays;
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

	public static void main(String[] args) {
		int size = 10; // 数据量
		int value = 100; // 近似值
		
		double[] arr = new double[size];
		for (int i = 0; i < size; i++) {
			arr[i] = Math.random() * 1000 - 500; // 随机生成-500 ~ 500
		}
		System.out.println(Arrays.toString(arr));
		double min = Math.abs(arr[0] - value);
		int index = 0;
		for(int i = 0; i< size; i++) {
			if(min > Math.abs(arr[i] - value)) {
				index = i;
				min = Math.abs(arr[i] - value);
			}
		}
		System.out.println("最小的绝对值：" + min);
		System.out.println("与100最接近的值：" + arr[index]);
	}
}
