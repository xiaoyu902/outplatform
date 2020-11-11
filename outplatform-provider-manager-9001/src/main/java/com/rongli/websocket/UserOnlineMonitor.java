package com.rongli.websocket;


import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;

import lombok.extern.slf4j.Slf4j;


/** webSocket服务器
 * @author WangZhiJun
 */
@Slf4j
@ServerEndpoint("/online/{useraccount}")
@Component
public class UserOnlineMonitor {
	/*
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private  static Map<String, UserOnlineMonitor> userOnlineHashMap = Collections.synchronizedMap(new HashMap<String, UserOnlineMonitor>());
  
    /*
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
    */
    private Session session;
 
    /**
     * 接收sid
     */
    private String useraccount;
    

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("useraccount") String useraccount) {
    	session.setMaxIdleTimeout(60*60*1000);
        this.session = session;
        this.useraccount=useraccount;
        userOnlineHashMap.put(useraccount, this);
        log.info("有新窗口开始监听:"+useraccount+",当前在线人数为" + getOnlineCount());
    }
    
    public static void login(String useraccount) {
    	 UserOnlineMonitor user=userOnlineHashMap.get(useraccount);//判断是否存在已登录账户
         if(user!=null&&user.session.isOpen()) {
         	//senMessage(ResultBody.failure("您的账号在其他终端登陆，您已被下线，请重新登录!").toString(), user.session);
         	user.onClose();//退出登录
         }
    }
 
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
   	 	//userOnlineHashMap.remove(useraccount);
        log.info(useraccount+"退出登录，有一连接关闭！当前在线人数为" + getOnlineCount());
    }
 
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	log.info("收到来自窗口【"+useraccount+"】的信息:"+message);
    	senMessage(message, session);	
    }
    
    public static void senMessage(String message, Session session) {
    	try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("推送消息异常:"+e.toString());
		}
    }
 
	/**
	 * 
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
    	log.error(useraccount+"发生错误:"+error.toString());
    }
    
    public static synchronized int getOnlineCount() {
        return userOnlineHashMap.size();
    }

}
