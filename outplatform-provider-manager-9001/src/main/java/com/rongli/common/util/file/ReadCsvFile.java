package com.rongli.common.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rongli.common.util.StringUtil;

public class ReadCsvFile {
	private static Log logj = LogFactory.getLog(ReadCsvFile.class);
	public static void main(String[] args) {
		ReadCsvFile r=new ReadCsvFile();
		r.test();
	}
	
	public static String Read(String path){
		 File file = new File(path);
		 InputStreamReader isr=null;
	      BufferedReader buff =null;
	      String str="";
	      try {
	          isr = new InputStreamReader(new FileInputStream(file), "GBK");
	           buff = new BufferedReader(isr);
	          String line=null;
	          //数据容器
	         
	          while ((line=buff.readLine())!=null) {
	              //去除`
	             // String replace = line.replace("`", "");
	        	  /*
	        	  if(line.indexOf("#")<0){
	        		  str+=line+"|";
	        		  System.out.println(line);
	        	  }else{
	        		  str+=line;
	        	  }*/
	        	  //System.out.println("##"+line);
	        	  str+=(StringUtil.replaceSpecialStr(line)+"\r\n");
	        	  
	          }
	        
	      } catch (Exception e) {
	          e.printStackTrace();
	          logj.info(e.toString());
	          return "";
	      } finally {
	          try {
	              if(isr!=null) {
	                  isr.close();
	              }
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	          try {
	              if(buff!=null) {
	                  buff.close();    
	              }
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }
	      
	      return str;
	}
	
	
	
	public void test(){
		
	  File file = new File("D://AliPayBills//2017-01-12//20889218358314800156_20180201_业务明细.csv");
      //解决中文乱码
      InputStreamReader isr=null;
      BufferedReader buff =null;
      try {
          isr = new InputStreamReader(new FileInputStream(file), "GBK");
           buff = new BufferedReader(isr);
          String line=null;
          //数据容器
          //List<WwXin> wxinlist=new ArrayList<WwXin>();
       //   WwXin wXin =null;
          while ((line=buff.readLine())!=null) {
              //去除`
              String replace = line.replace("`", "");
              String[] split = replace.split(",");
              System.out.println(replace);
              //去除后两行，第一行也是不需要存到数据库的，在后续操作中去掉第一条数据
              /*
              if(split.length==24) {
                  //符合要求
                  wXin=new WwXin();
                  wXin.setTime(split[0]);
                  wXin.setId(split[1]);
                  wXin.setShh(split[2]);
                  wXin.setZshh(split[3]);
                  wXin.setSbh(split[4]);
                  wXin.setWxddh(split[5]);
                  wXin.setShddh(split[6]);
                  wXin.setYhbs(split[7]);
                  wXin.setJylx(split[8]);
                  wXin.setJyzt(split[9]);
                  wXin.setFkyh(split[10]);
                  wXin.setHbzl(split[11]);
                  wXin.setZje(split[12]);
                  wXin.setDjjlj(split[13]);
                  wXin.setWxtkdh(split[14]);
                  wXin.setHztkdh(split[15]);
                  wXin.setTkje(split[16]);
                  wXin.setLjje(split[17]);
                  wXin.setTklx(split[18]);
                  wXin.setTkzt(split[19]);
                  wXin.setSbmc(split[20]);
                  wXin.setShsjb(split[21]);
                  wXin.setSxf(split[22]);
                  wXin.setFl(split[23]);
                  //添加
                  wxinlist.add(wXin);
              }*/
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }finally {
          try {
              if(isr!=null) {
                  isr.close();
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          try {
              if(buff!=null) {
                  buff.close();    
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
	}
}
