package com.rongli.driver.gzzl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.rongli.driver.HospTradeDriver;
import com.rongli.exception.BaseException;

@Component("gZZlTradeDriver")
public class GZZlTradeDriver extends HospTradeDriver{

	
	@Value("${org.gzzl.url}")
	private String remoteHost;
	
	@Override
	protected String submit(String tradeCode,String send) {
		// TODO Auto-generated method stub
		String orderStatus = "";
		HttpURLConnection conn = null;
		OutputStreamWriter out = null;
		try{
			URL url = new URL(remoteHost); //http://36.0.131.55:8081/TestFrame/index.jsp
			conn = (HttpURLConnection)url.openConnection(); //HttpURLConnection
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);//Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)
			conn.setReadTimeout(10000);//User-Agent	 Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
			conn.setUseCaches(false);
			conn.setFollowRedirects(true);
			conn.setInstanceFollowRedirects(false);  //不要内容跳转
			conn.setDoOutput(true);  
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型  
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8");  
            conn.setRequestProperty("Charset", "UTF-8"); 
            conn.setRequestProperty("contentType", "UTF-8"); 

//            //解锁号
//            String sendData = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>112</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><InHospitalNumber></InHospitalNumber><PatientId></PatientId><IdCard></IdCard><PageId></PageId><IsLeaveHospital>0</IsLeaveHospital><BeginTime></BeginTime><EndTime></EndTime><QueryType>0</QueryType></InBody></Input>";
//            //建档
//            String sendData11 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>201</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>433122198705178179</CardNo><Name>潘文杰</Name><Sex>1</Sex><Birthday>1987-05-17</Birthday><Address>湖南省长沙市岳麓区共和世家</Address><Phone>17673659659</Phone><IdCard>433122198705178179</IdCard><Nation>汉</Nation><PassWord></PassWord><IsNeonate>1</IsNeonate><ContactName>常鹤轩</ContactName><ContactIdCard>433122197408232330</ContactIdCard><ContactRelation></ContactRelation><CardTypeId>4</CardTypeId><IsBuyPatientIDCard></IsBuyPatientIDCard><BudgetFlag></BudgetFlag><BillMethod>3</BillMethod><SerialNumber></SerialNumber><Price></Price><NdividualPay></NdividualPay><BusinessExplain></BusinessExplain><PaySerialNumber></PaySerialNumber><PayChannel></PayChannel></InBody></Input>";
//            //查询
//            String sendData12 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>103</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo></CardNo><Name></Name><IdCard></IdCard><ContactName></ContactName><ContactIdCard></ContactIdCard><PatientId>39638</PatientId></InBody></Input>";
//            //回退当日挂号
//            String sendData1 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>208</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber>1911140001</PatientNumber><PatientId>217671</PatientId><SerialNumber>20191127091957134</SerialNumber><RetuensSerialNumber>156456121256462165</RetuensSerialNumber><BusinessExplain></BusinessExplain></InBody></Input>";
//            //回退预约挂号
//            String sendData2 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>210</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber>1911140001</PatientNumber><PatientId>217671</PatientId><SerialNumber>119112716483874945985</SerialNumber></InBody></Input>";
//            //挂号记录
//            String sendData3 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>110</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><BeginDate></BeginDate><EndDate></EndDate><SerialNumber></SerialNumber><IdCard></IdCard><PatientId></PatientId><Phone></Phone><BillNo></BillNo><OnlyReged>2</OnlyReged><RegistrationType>0</RegistrationType><PatientNumber></PatientNumber><DepartmentId></DepartmentId></InBody></Input>";
//            //检验列表
//            String sendData4 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>117</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><BeginApplyTime>2014-03-01 10:00:00</BeginApplyTime><EndApplyTime>2014-06-27 10:00:00</EndApplyTime><BeginReportTime>2014-03-01 10:30:00</BeginReportTime><EndReportTime>2014-06-27 10:00:00</EndReportTime><CardNo>00014543</CardNo><Name></Name><IdCard></IdCard><Phone></Phone><BillNo></BillNo><PatientSoure>0</PatientSoure><PatientId>25855</PatientId><OnlyList>0</OnlyList><PatientNumber></PatientNumber><InHospitalNumber>0</InHospitalNumber><MedicalId></MedicalId></InBody></Input>";
//            //余额查询
//            String sendData5 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>105</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><BalanceType>1</BalanceType></InBody></Input>";
//            //住院查询
//            String sendData6 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>112</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>00027536</CardNo><InHospitalNumber></InHospitalNumber><PatientId></PatientId><IdCard></IdCard><PageId></PageId><IsLeaveHospital>2</IsLeaveHospital><BeginTime></BeginTime><EndTime></EndTime><QueryType>1</QueryType></InBody></Input>";
//            //住院充值
//            String sendData7 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InBody><Money>0.01</Money><PaySerialNumber>119112616532486902823</PaySerialNumber><CardNo>432524199806076110</CardNo><AccountType>2</AccountType><BillMethod>3</BillMethod><Remark></Remark><PatientNumber></PatientNumber><InHospitalNumber>19110007</InHospitalNumber><SerialNumber>20191126165337400</SerialNumber><BudgetFlag>0</BudgetFlag><PayChannel>1</PayChannel></InBody><InHead><BusinessNumber>203</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead></Input>";
//            //住院费用明细
//            String sendData8 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>111</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><InHospitalNumber></InHospitalNumber><IdCard></IdCard><BeginTime></BeginTime><EndTime></EndTime></InBody></Input>";
//            //费用查询
//            String sendData9 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>104</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><ExpenseStatus>1</ExpenseStatus><BeginDate>2019-11-28</BeginDate>2019-11-29<EndDate></EndDate><BillNo></BillNo><QueryType>1</QueryType><PatientNumber></PatientNumber><PatientId></PatientId></InBody></Input>";
//            //生成缴费项
//            String sendData10 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>212</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>00027536</CardNo><ItemType>D</ItemType></InBody></Input>";
//            //取消挂号锁号
//            String sendData13 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>216</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber></PatientNumber><PatientId>217671</PatientId><SerialNumber>20200310110935060</SerialNumber></InBody></Input>";
//            
//            String sendData14 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>104</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>432524199902035415</CardNo><ExpenseStatus>3</ExpenseStatus><BeginDate>2020-06-27</BeginDate><EndDate>2020-07-30</EndDate><BillNo></BillNo><QueryType>0</QueryType><PatientNumber></PatientNumber><PatientId></PatientId></InBody></Input>";
//            String sendData15 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>101</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><LevelDepartmentId></LevelDepartmentId><IsMedicotechnical></IsMedicotechnical><DepartmentId></DepartmentId><Site></Site><OnlyParentDepartment></OnlyParentDepartment></InBody></Input>";
//   
            // 把数据写入请求的Body
            // 得到请求的输出流对象
            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");  
            PrintWriter pw = new PrintWriter(out);
            pw.write("strInput="+send);
            pw.flush();
            out.flush();
            out.close();  
            
            int code = conn.getResponseCode();
            if(code==200){
	            InputStream in  = conn.getInputStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int len  = 0 ;
				String result = "";
				byte[] buffer = new byte[1024];
				while((len = in.read(buffer))!= -1){
					outputStream.write(buffer, 0, len);
				}
				in.close();
				outputStream.close();
				byte[] resultArray = outputStream.toByteArray();
				String temp = new String(resultArray);
				if(temp.contains("utf-8") || temp.contains("UTF-8")){
					result = new String(resultArray,"UTF-8");
				}else if(temp.contains("gbk") || temp.contains("GBK") || temp.contains("gb2312")){
					result = new String(resultArray,"GBK");
				}else{
					result = new String(resultArray,"UTF-8");
				}
				return result;
            }else{
            	throw new BaseException(String.valueOf(code));
            }
		}catch(Exception ex){
			throw new BaseException("贵州中联his通讯异常:"+ex.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		queryOrder();
	}
	
	//查订单
	private static String queryOrder(){
		String orderStatus = "";
		HttpURLConnection conn = null;
		OutputStreamWriter out = null;
		try{
			
			URL url = new URL("http://test.zlgysoft.cn:9088/OneCardSolution.asmx/OneCardBusiness"); //http://36.0.131.55:8081/TestFrame/index.jsp
			conn = (HttpURLConnection)url.openConnection(); //HttpURLConnection
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);//Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)
			conn.setReadTimeout(10000);//User-Agent	 Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
			conn.setUseCaches(false);
			conn.setFollowRedirects(true);
			conn.setInstanceFollowRedirects(false);  //不要内容跳转
			conn.setDoOutput(true);  
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型  
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8");  
            conn.setRequestProperty("Charset", "UTF-8"); 
            conn.setRequestProperty("contentType", "UTF-8"); 

            //解锁号
            String sendData = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>112</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><InHospitalNumber></InHospitalNumber><PatientId></PatientId><IdCard></IdCard><PageId></PageId><IsLeaveHospital>0</IsLeaveHospital><BeginTime></BeginTime><EndTime></EndTime><QueryType>0</QueryType></InBody></Input>";
            //建档
            String sendData11 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>201</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>433122198705178179</CardNo><Name>潘文杰</Name><Sex>1</Sex><Birthday>1987-05-17</Birthday><Address>湖南省长沙市岳麓区共和世家</Address><Phone>17673659659</Phone><IdCard>433122198705178179</IdCard><Nation>汉</Nation><PassWord></PassWord><IsNeonate>1</IsNeonate><ContactName>常鹤轩</ContactName><ContactIdCard>433122197408232330</ContactIdCard><ContactRelation></ContactRelation><CardTypeId>4</CardTypeId><IsBuyPatientIDCard></IsBuyPatientIDCard><BudgetFlag></BudgetFlag><BillMethod>3</BillMethod><SerialNumber></SerialNumber><Price></Price><NdividualPay></NdividualPay><BusinessExplain></BusinessExplain><PaySerialNumber></PaySerialNumber><PayChannel></PayChannel></InBody></Input>";
            //查询
            String sendData12 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>103</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo></CardNo><Name></Name><IdCard></IdCard><ContactName></ContactName><ContactIdCard></ContactIdCard><PatientId>39638</PatientId></InBody></Input>";
            //回退当日挂号
            String sendData1 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>208</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber>1911140001</PatientNumber><PatientId>217671</PatientId><SerialNumber>20191127091957134</SerialNumber><RetuensSerialNumber>156456121256462165</RetuensSerialNumber><BusinessExplain></BusinessExplain></InBody></Input>";
            //回退预约挂号
            String sendData2 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>210</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber>1911140001</PatientNumber><PatientId>217671</PatientId><SerialNumber>119112716483874945985</SerialNumber></InBody></Input>";
            //挂号记录
            String sendData3 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>110</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><BeginDate></BeginDate><EndDate></EndDate><SerialNumber></SerialNumber><IdCard></IdCard><PatientId></PatientId><Phone></Phone><BillNo></BillNo><OnlyReged>2</OnlyReged><RegistrationType>0</RegistrationType><PatientNumber></PatientNumber><DepartmentId></DepartmentId></InBody></Input>";
            //检验列表
            String sendData4 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>117</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><BeginApplyTime>2014-03-01 10:00:00</BeginApplyTime><EndApplyTime>2014-06-27 10:00:00</EndApplyTime><BeginReportTime>2014-03-01 10:30:00</BeginReportTime><EndReportTime>2014-06-27 10:00:00</EndReportTime><CardNo>00014543</CardNo><Name></Name><IdCard></IdCard><Phone></Phone><BillNo></BillNo><PatientSoure>0</PatientSoure><PatientId>25855</PatientId><OnlyList>0</OnlyList><PatientNumber></PatientNumber><InHospitalNumber>0</InHospitalNumber><MedicalId></MedicalId></InBody></Input>";
            //余额查询
            String sendData5 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>105</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><BalanceType>1</BalanceType></InBody></Input>";
            //住院查询
            String sendData6 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>112</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>00027536</CardNo><InHospitalNumber></InHospitalNumber><PatientId></PatientId><IdCard></IdCard><PageId></PageId><IsLeaveHospital>2</IsLeaveHospital><BeginTime></BeginTime><EndTime></EndTime><QueryType>1</QueryType></InBody></Input>";
            //住院充值
            String sendData7 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InBody><Money>0.01</Money><PaySerialNumber>119112616532486902823</PaySerialNumber><CardNo>432524199806076110</CardNo><AccountType>2</AccountType><BillMethod>3</BillMethod><Remark></Remark><PatientNumber></PatientNumber><InHospitalNumber>19110007</InHospitalNumber><SerialNumber>20191126165337400</SerialNumber><BudgetFlag>0</BudgetFlag><PayChannel>1</PayChannel></InBody><InHead><BusinessNumber>203</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead></Input>";
            //住院费用明细
            String sendData8 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>111</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><InHospitalNumber></InHospitalNumber><IdCard></IdCard><BeginTime></BeginTime><EndTime></EndTime></InBody></Input>";
            //费用查询
            String sendData9 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>104</BusinessNumber><TerminalNumber>SPTHKZY</TerminalNumber><CooperationUnit>慧康智云</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><ExpenseStatus>1</ExpenseStatus><BeginDate>2019-11-28</BeginDate>2019-11-29<EndDate></EndDate><BillNo></BillNo><QueryType>1</QueryType><PatientNumber></PatientNumber><PatientId></PatientId></InBody></Input>";
            //生成缴费项
            String sendData10 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>212</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>00027536</CardNo><ItemType>D</ItemType></InBody></Input>";
            //取消挂号锁号
            String sendData13 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>216</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>432524199806076110</CardNo><PatientNumber></PatientNumber><PatientId>217671</PatientId><SerialNumber>20200310110935060</SerialNumber></InBody></Input>";
            
            String sendData14 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>104</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><CardNo>432524199902035415</CardNo><ExpenseStatus>3</ExpenseStatus><BeginDate>2020-06-27</BeginDate><EndDate>2020-07-30</EndDate><BillNo></BillNo><QueryType>0</QueryType><PatientNumber></PatientNumber><PatientId></PatientId></InBody></Input>";
            String sendData15 = "strInput=<?xml version=\"1.0\" encoding=\"utf-8\"?><Input><InHead><BusinessNumber>101</BusinessNumber><TerminalNumber>CS001</TerminalNumber><CooperationUnit>测试平台</CooperationUnit></InHead><InBody><LevelDepartmentId></LevelDepartmentId><IsMedicotechnical></IsMedicotechnical><DepartmentId></DepartmentId><Site></Site><OnlyParentDepartment></OnlyParentDepartment></InBody></Input>";
            
            System.out.println("订单查询请求报文："+sendData11);
            // 把数据写入请求的Body
            // 得到请求的输出流对象
            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");  
            PrintWriter pw = new PrintWriter(out);
            pw.write(sendData12);
            pw.flush();
            out.flush();
            out.close();  
            
            int code = conn.getResponseCode();
            if(code==200){
	            InputStream in  = conn.getInputStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				int len  = 0 ;
				String result = "";
				try {
						byte[] buffer = new byte[1024];
						while((len = in.read(buffer))!= -1){
							outputStream.write(buffer, 0, len);
						}
						in.close();
						outputStream.close();
						byte[] resultArray = outputStream.toByteArray();
						String temp = new String(resultArray);
						if(temp.contains("utf-8") || temp.contains("UTF-8")){
							result = new String(resultArray,"UTF-8");
						}else if(temp.contains("gbk") || temp.contains("GBK") || temp.contains("gb2312")){
							result = new String(resultArray,"GBK");
						}else{
							result = new String(resultArray,"UTF-8");
						}
						System.out.println(result);
				}catch(Exception ex){
					System.out.println("异常一");
					ex.printStackTrace();
				}
            }else{
            	System.out.println(code);
            }
		}catch(Exception ex){
			System.out.println("异常二");
			ex.printStackTrace();
		}
		return orderStatus;
	}

}
