package com.rongli.config.websocket;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.util.WebAppRootListener;
/**
 * 开启WebSocket支持
 * @author zhengkai
 */
@Configuration 
public class WebSocketConfig implements ServletContextInitializer{
	 /**
     *  注入ServerEndpointExporter，
     *  这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     *  
     *  在外部tomcat运行如果将ServerEndpointExporter注入spring容器会导致报错如下
     *  Caused by: org.springframework.beans.factory.BeanCreationException: Error creati
ng bean with name 'serverEndpointExporter' defined in class path resource [cn/rl
/common/WebSocketConfig.class]: Invocation of init method failed; nested excepti
on is java.lang.IllegalStateException: javax.websocket.server.ServerContainer no
t available

所以在外部tomcat运行就不需要注入该对象，如果是eclipse运行则需要注入对象，通过WebscoketAutoWired控制
     */
    @Bean
    @Conditional(WebscoketAutoWired.class)
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize","1024000");
	}  
  
}