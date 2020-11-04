package com.rongli.common.util;
import java.text.*; 
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import com.rongli.common.exception.BaseException;
public class DateUtil {
	public static String DEFAULT_FORMAT = "yyyy-MM-dd";
	
	public static String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	/**
	 * 格式化日期
	 * @param date 日期对象
	 * @return String 日期字符串
	 */
	public static String formatDate(Date date){
		SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
		String sDate = f.format(date);
		return sDate;
	}
	/** 
	* 获取现在时间 
	* 
	* @return 返回时间类型 yyyy-MM-dd HH:mm:ss 
	*/ 
	public static Date getNowDate() { 
		Date currentTime = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateString = formatter.format(currentTime); 
		ParsePosition pos = new ParsePosition(8); 
		Date currentTime_2 = formatter.parse(dateString, pos); 
		return currentTime_2; 
	} 
	
	/** 
	* 获取现在时间 
	* 
	* @return 返回短时间字符串格式yyyy-MM-dd 
	*/ 
	public static String getStringDateShort() { 
		Date currentTime = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		String dateString = formatter.format(currentTime); 
		return dateString; 
	} 
	
	/** 
	* 获取时间 小时:分;秒 HH:mm:ss 
	* 
	* @return 
	*/ 
	public static String getTimeShort() { 
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT); 
		Date currentTime = new Date(); 
		String dateString = formatter.format(currentTime); 
		return dateString; 
	} 
	
	/** 
	* 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss 
	* 
	* @param strDate 
	* @return 
	*/ 
	public static Date strToDateLong(String strDate) { 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		ParsePosition pos = new ParsePosition(0); 
		Date strtodate = formatter.parse(strDate, pos); 
		return strtodate; 
	} 
	
	/** 
	* 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss 
	* 
	* @param dateDate 
	* @return 
	*/ 
	public static String dateToStrLong(Date dateDate) { 
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	String dateString = formatter.format(dateDate); 
	return dateString; 
	} 

	/** 
	* 将短时间格式字符串转换为时间 yyyy-MM-dd 
	* 
	* @param strDate 
	* @return 
	*/ 
	public static Date strToDate(String strDate) { 
	SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT); 
	ParsePosition pos = new ParsePosition(0); 
	Date strtodate = formatter.parse(strDate, pos); 
	return strtodate; 
	} 
	
	/** 
	* 将短时间格式字符串转换为时间 yyyy-MM-dd 
	* 
	* @param strDate 
	* @return 
	*/ 
	public static String formatDateStr(String strDate) { 
	String year=strDate.substring(0, 4);
	String month=strDate.substring(4, 6);
	String day=strDate.substring(6, 8);
	return year+"-"+month+"-"+day; 
	} 
	
	public static String formatTimeStr(String strTime) { 
		String hour=strTime.substring(0, 2);
		String minute=strTime.substring(2, 4);
		String second=strTime.substring(4, 6);
		return hour+":"+minute+":"+second; 
		} 
	
	public static String getFutureDate(int days){
   	 	String temp_str="";   
	    Date dt = new Date();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(dt);
	    calendar.add(calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
	    dt=calendar.getTime(); //这个时间就是日期往后推一天的结果 
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt);  
	    return temp_str;
   }
	
	/** 
	* 获得指定日期的前一天 
	* @param specifiedDay 
	* @return 
	* @throws Exception 
	*/ 
	public static String getFutureDate(String specifiedDay,int days){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+days); 
	
		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
	} 
	
	/*获取指定年份的第一天*/
	public static String getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return formatDate(currYearFirst);
	}
	
	public static List<String> getMonthListByYear(String year){
		 	String temp_str="";   
		    Date dt = new Date();   
		    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");   
		    temp_str=sdf.format(dt);  
		    String total_year=temp_str.substring(0,temp_str.indexOf("-"));
		    String total_month=temp_str.substring(temp_str.indexOf("-")+1,temp_str.length());
		if(total_year.equals(year)){
			List<String> list=new ArrayList<String>();
			for(int i=1;i<=Integer.parseInt(total_month);i++){
				//list.add(year+"-"+(i<10?"0"+i:i));
				list.add(year+"-"+i);
			}
			return list;
		}else{
			List<String> list=new ArrayList<String>();
			for(int i=1;i<=12;i++){
				list.add(year+"-"+i);
			}
			return list;
		}
	}
	
	/*获取指定年份的最后一天*/
	public static String getYearLast(int year){
		if(year==Integer.parseInt(getNowYear())){//如果是获取的年份是当前年份最后一天就是今天
			return getNowDateStr();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return formatDate(currYearLast);
	}
	
	public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
	
	public static int CompareTo(String start,String end){
		
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            //将日期转成Date对象作比较
	            Date fomatDate1=sdf.parse(start);
	            Date fomatDate2=sdf.parse(end);
	            //比较两个日期
	            int result=fomatDate2.compareTo(fomatDate1);
	            //如果日期相等返回0
	            if(result==0){
	                return 0;
	            }else if(result<0){
	                //小于0，参数date1就是在date2之后
	                return 1;
	            }else{
	                //大于0，参数date1就是在date2之前
	               return -1;
	            }
	             
	        } catch (ParseException e) {
	            System.out.println(e.toString());
	           return -999;
	        }
	}
	public static String getNowDateStr(){   
	    String temp_str="";   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt);   
	    return temp_str;   
	} 
	
	public static String getNowDateTimeStr(){   
	    String temp_str="";   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	    temp_str=sdf.format(dt);   
	    return temp_str;   
	} 
	
	
	public static List<String> getFullYearList(String year){
		List<String> list=new ArrayList<String>();
		for(int i=1;i<=12;i++){
			if(year.equals(getNowYear())){//如果是传入年份和当前年份一致则需要判断有多少月份
				if(i>getNowMonth()){
					break;
				}
			}
			list.add(String.valueOf(i));
		}

		return list;
	}
	
	public static List<String> getFullYearList2(String year){
		List<String> list=new ArrayList<String>();
		for(int i=1;i<=12;i++){
			if(year.equals(getNowYear())){//如果是传入年份和当前年份一致则需要判断有多少月份
				if(i>getNowMonth()){
					break;
				}
			}
			list.add(String.valueOf(i)+"月");
		}

		return list;
	}
	
	public static int getNowMonth(){
		String date=getNowDateStr();
		String month=date.substring(5,7);
		return Integer.parseInt(month);
	}
	
	public static String getNowYear(){
		String date=getNowDateStr();
		String year=date.substring(0,4);
		return year;
	}
	
public static List<String> getMonthFullDay(String date){
		
	    SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
	    List<String> fullDayList = new ArrayList<String>();
	    try{
		    int year=Integer.parseInt(date.substring(0, date.indexOf("-")));
			int month=Integer.parseInt(date.substring(date.indexOf("-")+1, date.length()));
		    // 获得当前日期对象
		    Calendar cal = Calendar.getInstance();
		    cal.clear();// 清除信息
		    cal.set(Calendar.YEAR, year);
		    // 1月从0开始
		    cal.set(Calendar.MONTH, month-1 );
		    // 当月1号
		    cal.set(Calendar.DAY_OF_MONTH,1);
		    int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		    for (int j = 1; j <= count ; j++) {
		    	if(dateFormatYYYYMMDD.format(cal.getTime()).compareTo(getNowDateStr())==0){//不能大于当前天，如果需要大于等于当前日期，则判断>0
		    		break;
		    	}
		        fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
		        cal.add(Calendar.DAY_OF_MONTH,1);
		    }
		    
	    }catch(Exception e){
	    	System.out.println(e.toString());
	    }
	    return fullDayList;
	}

public static int daysBetween(String time1, String time2){
	int days = 0;
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	try {
	Date date1 = ft.parse(time1);
	Date date2 = ft.parse(time2);
	 days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
     return days;
	
	} catch (ParseException e) {
	e.printStackTrace();
	}
	
	return  days ;
}

public static int hoursBetween(Date arg0, Date arg1){
	int hours=0;
	try {
		hours = (int) ((arg0.getTime() - arg1.getTime()) / (1000*3600));
        return hours;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return  hours ;
}

public static boolean isValidDate(String str) {
    boolean convertSuccess = true;
    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try {
        // 设置lenient为false.
        // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
        format.setLenient(false);
        format.parse(str);
    } catch (ParseException e) {
        // e.printStackTrace();
        // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
        convertSuccess = false;
    }
    return convertSuccess;
}

	public static boolean compare(String beginTime, String endTime) {
		boolean flag = false;

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		 
		try {
			 Date bt = sdf.parse(beginTime);
			 Date et=sdf.parse(endTime); 
			 if (bt.before(et)){ 
				 flag=false;
			}else{ 
				flag=true;
			} 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 return flag;
		 
	}


	public static List<String> sortDateList(List<String> list) {
		if(list==null) {
			throw new BaseException("list为空");
		}else if(list.isEmpty()) {
			return list;
		}
		list = new ArrayList<String>(new HashSet<String>(list)); 
		
		List<String> newlist = new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			if(!newlist.contains(list.get(i))){
				newlist.add(list.get(i));
			}
		}
		String tmp;
		for(int i=1; i<newlist.size(); i++){
				tmp = newlist.get(i);
				int j=i-1;
				for(; j>=0&&compare(tmp, newlist.get(j)); j--){
					newlist.set(j+1, newlist.get(j));
				}
				newlist.set(j+1, tmp);
		}
		
		return newlist;
	}
	
	/**
	 * 得到yyyyMMddHHmmssSSS格式的现在的时间
	 * @return
	 */
	public static String getYYYYMMddHHMMssSSS(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	

	public static String timeStamp2Date(String date) {
		 String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
	        date = date.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
	       return date; 
	}
	
	/**
	 * 时间比较
	 * @param time1 time2 比较日期
	 * @return boolean true和false
	 * @throws KyRuntimeException 
	 */
	public static boolean compareTime(String time1,String time2){
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		try {
			Date dt1 = df.parse(time1);//将字符串转换为date类型
			Date dt2 = df.parse(time2);
			if(dt1.getTime()>dt2.getTime())//比较时间大小,如果dt1大于dt2
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new BaseException(DEFAULT_TIME_FORMAT+"时间串比较异常:"+e.getMessage());
		}
	}
	
	public static boolean isValidTime(String str) { 
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		try{ 
			Date date = (Date)df.parse(str); 
			return str.equals(df.format(date)); 
		}catch(Exception e){ 
			return false; 
		} 
	}
	
	//计算两个时间时间差
	public static String TimeSubtract(String startTime,String endTime){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);
            //相隔天数的总毫秒数
            long diff = d1.getTime() - d2.getTime();
            //天数 (1000 * 60 * 60 * 24)一天的毫秒数
            long days = diff / (1000 * 60 * 60 * 24);
            //小时，(1000* 60 * 60)一小时的毫秒数
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            //分, (1000* 60)一分钟毫秒数
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            //秒 ,1000为1秒钟的毫秒数
            long ss = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60)-minutes*(1000* 60))/1000;
//            long ss = (diff % (1000 * 60 * 60 * 24) % (1000* 60 * 60) % (1000* 60) / 1000);
            if(days==0) {
            	if(hours==0) {
            		if(minutes==0) {
            			return ss+"秒";
            		}else {
            			return minutes+"分"+ss+"秒";
            		}
            	}else {
            		return hours+"小时"+minutes+"分"+ss+"秒";
            	}
            }else {
            	 return (days+"天"+hours+"小时"+minutes+"分"+ss+"秒");
            }
           
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "0秒";
    }


	  
	  /**
	     * 将毫秒字符串转成时间格式yyyy-MM-dd HH:mm:ss
	     * @param time
	     * @return
	     */
	    public static String getTimeFormat(String timeStr) {
	        long time=Long.parseLong(timeStr);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(time);
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH) + 1;
	        String monthStr = addZero(month);
	        int day = calendar.get(Calendar.DAY_OF_MONTH);
	        String dayStr = addZero(day);
	        int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
	        String hourStr = addZero(hour);
	        int minute = calendar.get(Calendar.MINUTE);
	        String minuteStr = addZero(minute);
	        int second = calendar.get(Calendar.SECOND);
	        String secondStr =addZero(second);
	        return(year + "-" + monthStr  + "-" + dayStr + " "
	                + hourStr + ":" + minuteStr + ":" + secondStr);
	    }
	    private static String addZero(int param) {
	        String paramStr= param<10 ? "0"+param : "" + param ;
	        return paramStr;
	    }
	    /**
	     * 将时间格式yyyy-MM-dd HH:mm:ss转成毫秒
	     * @param time
	     * @return
	     * @throws ParseException 
	     */
	    public static Long getTimeFormat2mill(String timeStr) throws ParseException {
	         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Date date = simpleDateFormat.parse(timeStr);
	         long ts = date.getTime();
	         return ts;
	    }
	    /**
	     * 将时间格式yyyyMMddHHmmss转成毫秒
	     * @param time
	     * @return
	     * @throws ParseException 
	     */
	    public static Long getTimeString2mill(String timeStr) throws ParseException {
	         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	         Date date = simpleDateFormat.parse(timeStr);
	         long ts = date.getTime();
	         return ts;
	    }
	    /**
	     * 将毫秒转成时间串yyyyMMddHHmmss
	     * @param time
	     * @return
	     * @throws ParseException 
	     */
	    public static String getmill2TimeString(String timeStr) throws ParseException {
	         return getTimeFormat(timeStr).replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
	    }
	    /**
	     * 将时间格式yyyyMMddHHmmss转成将时间格式yyyy-MM-dd HH:mm:ss
	     * @param time
	     * @return
	     * @throws ParseException 
	     */
	    public static String getTimeString2TimeFormat(String timeStr) throws ParseException {
	         return getTimeFormat(""+getTimeString2mill(timeStr));
	    }
	    /**
	     * 将时间格式yyyy-MM-dd HH:mm:ss转成将时间格式yyyyMMddHHmmss
	     * @param time
	     * @return
	     * @throws ParseException 
	     */
	    public static String getTimeFormat2TimeString(String timeFormat) throws ParseException {
	    		return getmill2TimeString(""+getTimeFormat2mill(timeFormat));
	    }
	    /**
	     * 计算两个时间的间隔,返回分钟数值，不足一分钟舍去，计算的时间为标准时间格式yyyy-MM-dd HH:mm:ss
	     * @param time
	     * @return
	     */
	    public static String getIntevalTime(String startTime,String endTime) throws ParseException {
	        long intevaltime=getTimeFormat2mill(endTime)-getTimeFormat2mill(startTime);
	        return ""+intevaltime/60000;
	    }
	    /**
	     * 将分钟数值转成时间字符串
	     * @param time
	     * @return
	     */
	    public static String minute2Hour(String min){
	        int num=Integer.parseInt(min);
	        int hour=num/60;
	        int minute=num%60;
	        if (hour==0) {
	            return minute+"分钟";
	        }
	        return hour+"小时"+minute+"分钟";
	    }

	public static void main(String[] args) {
		try {
			System.out.println(getTimeFormat2TimeString("2020-09-04 10:57:11"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
