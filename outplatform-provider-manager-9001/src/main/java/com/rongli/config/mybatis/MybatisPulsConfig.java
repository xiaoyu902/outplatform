package com.rongli.config.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;

@Configuration
public class MybatisPulsConfig {


	   // 返回map自动转驼峰命名
	   @Bean
	   public ConfigurationCustomizer mybatisConfigurationCustomizer(){
	   		return new ConfigurationCustomizer() {
		
				@Override
				public void customize(MybatisConfiguration configuration) {
					// TODO Auto-generated method stub
					configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
				}
			};
	   }
}
