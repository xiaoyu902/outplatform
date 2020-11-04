package com.rongli.filter;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;

import io.netty.buffer.ByteBufAllocator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CheckSignFilter implements  GlobalFilter,Ordered {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		ServerHttpRequest request = exchange.getRequest();
	    ServerHttpResponse response = exchange.getResponse();
		
		// TODO Auto-generated method stub
		String serviceId=request.getQueryParams().getFirst("serviceId");
		System.out.println("****************** come in filter *******************");
		System.out.println("serviceId:"+serviceId);
		
		 
		if(StringUtils.isEmpty(serviceId)) {
	            return response.writeWith(Mono.just(retData(response, "服务号不能为空")));
		}

		return chain.filter(exchange);
	}

	
	private DataBuffer retData(ServerHttpResponse response,String msg) {
		  JSONObject message = ResultBody.failure(msg).toJson();
          byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
          DataBuffer buffer = response.bufferFactory().wrap(bits);
          response.setStatusCode(HttpStatus.UNAUTHORIZED);
          //指定编码，否则在浏览器中会中文乱码
          response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
          return buffer;
	}
}
