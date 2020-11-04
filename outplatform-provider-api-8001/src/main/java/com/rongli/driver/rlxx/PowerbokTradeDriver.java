package com.rongli.driver.rlxx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rongli.driver.HospTradeDriver;
import com.rongli.driver.rlxx.hiswebs.HisService;
import com.rongli.driver.rlxx.hiswebs.HisServiceImpService;
import com.rongli.exception.BaseException;

@Component
public class PowerbokTradeDriver extends HospTradeDriver{

	
	@Value("${org.powerbok.url}")
	private String remoteHost;
	
	@Override
	protected String submit(String tradeCode,String send) {
		// TODO Auto-generated method stub
		String strResp="";
		try {
			String ServiceURL = remoteHost;
			System.out.println("ServiceURL:"+ServiceURL);
			HisServiceImpService his = new HisServiceImpService();
			HisService hisService = his.getHisServiceImpPort();
			 if(tradeCode.equals("S3001")){
				 strResp=hisService.getPatientInfo(send);
			 }else{
				 throw new BaseException("未定义的接口");
			 }
			
			return strResp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("荣利webservice交互失败"+e.getMessage());
		}
	}

}
