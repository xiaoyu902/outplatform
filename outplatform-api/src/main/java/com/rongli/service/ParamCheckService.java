package com.rongli.service;

import com.alibaba.fastjson.JSONObject;

public interface ParamCheckService {

	public JSONObject checkChannel(String type);
	
	public JSONObject checkCertType(String data);

	public JSONObject checkPaytype(String data);
	
	public JSONObject checkSex(String data);

}
