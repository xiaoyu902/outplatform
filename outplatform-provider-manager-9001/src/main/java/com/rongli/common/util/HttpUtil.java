package com.rongli.common.util;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;



public class HttpUtil {
	public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {  
	    // 读取请求内容  
	    /*
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));  
	    String line = null;  
	    StringBuilder sb = new StringBuilder();  
	    while((line = br.readLine())!=null){  
	        sb.append(line);  
	    }  
	    // 将资料解码  
	    String reqBody = sb.toString(); 
	     */
	    
	    ByteArrayOutputStream os = new ByteArrayOutputStream(4096);//最大 4M数据
	    byte[] b = new byte[1024];
	    ServletInputStream in = request.getInputStream();
	    int pos = -1;
	    while((pos=in.read(b))!=-1){
	    	os.write(b, 0, pos);
	    }
	    byte[] retArr = os.toByteArray();
	    String reqBody = new String(retArr,"UTF-8");
	    
	    return reqBody;
	} 	
	
	public static String recvBackInfo(HttpServletRequest request)throws Exception {
        BufferedReader reader = null;
        String line = "";
        String xmlString = null;
      
            reader = request.getReader();
            StringBuffer inputString = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            xmlString = inputString.toString();
       
        return xmlString;
    }

	// 将request中的参数转换成Map
    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

	
	public static Object send(String path,String sendXml) throws Exception{
		//String jsonMenu=JSONObject.fromObject(menu).toString();
		
		String message="";
		//System.out.println("发送订单："+sendXml);
		
		//String path="https://api.mch.weixin.qq.com/pay/closeorder";

		URL url=new URL(path);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setDoOutput(true);
		http.setDoInput(true);
		http.setRequestMethod("POST");
		http.setConnectTimeout(20000);
		http.setRequestProperty("Content-Type","application/json; charset=utf-8");
		http.connect();
		OutputStream os = http.getOutputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		os.write(sendXml.getBytes("UTF-8"));
		os.close();
		
		InputStream is = http.getInputStream();
		int len = 0;
		byte[] bt = new byte[1024];
		while((len=is.read(bt))!=-1){
			out.write(bt, 0, len);
		}
		
		message=out.toString("UTF-8");
		
		http.disconnect();  
		//System.out.println("接收订单:"+message);

	
		return message;
	}

	
	/**
	  * 通过Https往API post xml数据
	  * @param url   API地址
	  * @param xmlObj   要提交的XML数据对象
	  * @param path    当前目录，用于加载证书
	  * @return
	  * @throws IOException
	  * @throws KeyStoreException
	  * @throws UnrecoverableKeyException
	  * @throws NoSuchAlgorithmException
	  * @throws KeyManagementException
	  */
	 public static String httpsRequest(RequestConfig requestConfig,CloseableHttpClient httpClient,String url, String xmlObj, String path ) throws Exception {


	     String result = null;

	     HttpPost httpPost = new HttpPost(url);

	     //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
	     StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
	     httpPost.addHeader("Content-Type", "text/xml");
	     httpPost.setEntity(postEntity);

	     //设置请求器的配置
	     httpPost.setConfig(requestConfig);


	         HttpResponse response = httpClient.execute(httpPost);

	         HttpEntity entity = response.getEntity();

	         result = EntityUtils.toString(entity, "UTF-8");

	    
	         httpPost.abort();
	     

	     return result;
	 }
	 
	 /**
	     * 判断是否是Ajax请求
	     *
	     * @param request
	     * @return
	     */
    public boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

	 
}
