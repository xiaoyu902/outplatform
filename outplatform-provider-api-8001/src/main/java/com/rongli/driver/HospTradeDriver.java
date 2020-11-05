package com.rongli.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.entities.params.ServiceResult;
import com.rongli.exception.BaseException;
import com.rongli.service.DataHandleService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public abstract class HospTradeDriver {

	protected abstract String submit(String serviceId,String send);
	
	
	@Autowired
	private DataHandleService dataHandleService;
	
	public String call(String serviceId,String send) {
		if(StringUtils.isEmpty(send))
			throw new BaseException("发送数据为空");
		
		log.info("发送his报文:{}",send);
		String ret= submit(serviceId,send);
		log.info("接收his报文:{}",ret);
		return ret;
	}

	
	public ServiceResult doTrade(ServiceEntity se,JSONObject recv) {
		ServiceResult sr=new ServiceResult();
		try {
			if(se==null)
				throw new BaseException("api实体类为空");
			
			if(recv==null)
				throw new BaseException("接收数据为空");
			
			sr.setFromThirdData(recv.toJSONString());
			
			JSONObject dbData=new JSONObject();
			try {
				sr.setToHisData(dataHandleService.conversionInput(se, recv, dbData));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new BaseException("入参转化失败:"+e.getMessage());
			}
			
			try {
				String fromHisData=this.call(se.getServiceId(),sr.getToHisData());
				
				if(StringUtils.isEmpty(fromHisData))
					throw new BaseException("接收数据为空");
				
				sr.setFromHisData(fromHisData);
				
			}catch (Exception e) {
				// TODO: handle exception
				throw new BaseException("通讯异常:"+e.getMessage());
			}
			
			try {
				String toThirdData= dataHandleService.conversionOutput(se, sr.getFromHisData(), dbData);
				sr.setDbData(dbData);
				sr.setToThirdData(toThirdData);
			}catch (Exception e) {
				// TODO: handle exception
				throw new BaseException("解析数据失败:"+e.getMessage());
			}
			sr.setResult(true);
			sr.setMsg("交易成功");
		}catch (Exception e) {
			sr.setResult(false);
			sr.setMsg("交易失败:"+e.getMessage());
			sr.setToThirdData(ResultBody.error(sr.getMsg()).toString());
		}
		return sr;
	}
}
