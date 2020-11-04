package com.rongli.entities;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rongli.common.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatorLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String useraccount,ip,method,signature,args,result,alltime,createtime,remark;
	
	public Map<String, Object> toMap() {
		return JsonUtil.entityToMap(this);
	}
	
	public String toJson() {
		return JSONObject.toJSONString(this);
	}
}
