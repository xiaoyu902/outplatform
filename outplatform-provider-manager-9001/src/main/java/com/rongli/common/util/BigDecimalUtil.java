package com.rongli.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

	/** 
     * BigDecimal的加法运算封装 
     * @author whd
     * @param b1 
     * @param bn 
     */  
   public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {  
       if (null == b1) {  
           b1 = BigDecimal.ZERO;  
       }  
       if (null != bn) {  
           for (BigDecimal b : bn) {  
               b1 = b1.add(null == b ? BigDecimal.ZERO : b);  
           }  
       }  
       return b1;  
   } 
   
   /** 
    * BigDecimal的安全减法运算 
    * @author :whd 
    * @param isZero  减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果 
    * @param b1      被减数 
    * @param bn      需要减的减数数组 
    * @return 
    */  
   public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {  
       if (null == b1) {  
           b1 = BigDecimal.ZERO;  
       }  
       BigDecimal r = b1;  
       if (null != bn) {  
           for (BigDecimal b : bn) {  
               r = r.subtract((null == b ? BigDecimal.ZERO : b));  
           }  
       }  
       return isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : r) : r;  
   }  
   
   /** 
    * BigDecimal的乘法运算封装 
    * @author : whd
    * @param b1 
    * @param b2 
    * @return 
    */  
   public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {  
       if (null == b1 ||  null == b2) {  
           return BigDecimal.ZERO;  
       }  
       return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);  
   }
   
   /** 
    * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值 
    * 默认返回小数位后2位，用于金额计算 
    * @author :whd
    * @param b1 
    * @param b2 
    * @param defaultValue 
    * @return 
    */  
   public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {  
       if (null == b1 ||  null == b2) {  
           return defaultValue;  
       }  
       try {  
           return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_HALF_UP);  
       } catch (Exception e) {  
           return defaultValue;  
       }  
   }
   
   public static BigDecimal safeAbsolute(BigDecimal value){
	   return value.multiply(new BigDecimal(-1));
   } 
   
   public static BigDecimal fen2yuan(BigDecimal fee) {
	   fee = fee.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);//分转元
	   return  fee;
   }
}
