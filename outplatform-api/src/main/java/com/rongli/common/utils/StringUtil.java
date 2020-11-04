package com.rongli.common.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {
	
	public static String getDriverName(String path) {//获取驱动名
		String s=path;
		String driverName=s.substring(s.lastIndexOf(".")+1, s.length());
		String firstChar=driverName.substring(0, 1).toLowerCase();
		String leftChar=driverName.substring(1, driverName.length());
		driverName=firstChar+leftChar;
		return driverName;
	}
	
	public static boolean compare(String key1,String key2) {
		if(key1.compareTo(key2)==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean IsValidDate(String str) {
	      boolean convertSuccess=true;
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       try {
	    	   format.setLenient(false);
	          format.parse(str);
	       } catch (Exception e) {
	       
	           convertSuccess=false;
	       } 
	       return convertSuccess;
	}
	
	public static boolean isNumeric(String str) {
		try{
		 Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
	        Matcher match=pattern.matcher(str); 
	        if(match.matches()==false){ 
	           return false; 
	        }else{ 
	           return true; 
	        }
		}catch(Exception e){
			log.error("判断金额字符串格式是否正确异常:"+e.toString());
			return false;
		}
	}
	
	//判断的方法  
	public static boolean isPhoneNumberValid(String phone) {
		if(isPhone(phone) || isMobile(phone)){
		     return true;
		  }else{
			  return false;
		  }

	
	}
	
	 /**
	   * 手机号验证
	   * @author ：shijing
	   * 2016年12月5日下午4:34:46
	   * @param  str
	   * @return 验证通过返回true
	   */
	  public static boolean isMobile(final String str) {
	      Pattern p = null;
	      Matcher m = null;
	      boolean b = false;
	      p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
	      m = p.matcher(str);
	      b = m.matches();
	      return b;
	  }

	  public static boolean isPhone(final String str) {
	      Pattern p1 = null, p2 = null;
	      Matcher m = null;
	      boolean b = false;
	      p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
	      p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
	      if (str.length() > 9) {
	         m = p1.matcher(str);
	         b = m.matches();
	      } else {
	          m = p2.matcher(str);
	         b = m.matches();
	      }
	      return b;
	  }

	public static String replaceSpecialStr(String str) {
	        String repl = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\t|\r|\n");
	            Matcher m = p.matcher(str);
	            repl = m.replaceAll("");
	        }
	        return repl;
	}

	public static String DateToString(Date date){
		return null;
		
	}
	
	public static BigDecimal StringtoBigDecimal(String str){
		BigDecimal a=new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return a;
	}
	


	public static String getRandom() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 判断是JsonObject
	 * @param obj
	 * @return
	 */
	public static boolean isJsonObject(Object obj){
		String content = obj.toString();
	    try {
	        JSONObject.parseObject(content);
	        if (content.startsWith("{")) {
	        	return  true;
			}else {
				return  false;
			}
	   } catch (Exception e) {
	        return false;
	  }
	}
	
	/**
	 * 判断是JsonArray
	 * @param obj
	 * @return
	 */
	public static boolean isJsonArray(Object obj){
		String content = obj.toString();
	    try {
	        JSONArray.parseArray(content);
	        if (content.startsWith("[")) {
	        	return  true;
			}else {
				return  false;
			}
	   } catch (Exception e) {
	        return false;
	  }
	}

}
