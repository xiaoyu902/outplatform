package com.rongli.config.websocket;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否使用webscoket容器，如果使用的是生产环境且是war包的方式不装配bean，使用方式@Conditional(WarAndJarWebscoketAutoWired.class)
 */
public class WebscoketAutoWired implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// TODO Auto-generated method stub
		Environment env = context.getEnvironment();
    
        String active = env.getProperty("spring.profiles.active");
 //               return !packageStyle.equals("war");
       return !(active.equals("prod"));
	}
}
