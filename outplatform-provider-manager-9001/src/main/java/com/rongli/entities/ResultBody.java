package com.rongli.entities;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rongli.common.exception.BaseErrorInfoInterface;
import com.rongli.common.util.JsonUtil;
import com.rongli.entities.enums.CommonEnum;

import lombok.Data;

@Data
public class ResultBody {
	/**
	 * 响应代码
	 */
	private String code;

	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 响应结果
	 */
	private Object data;
	
	private Long count;
	
	public ResultBody() {
	}

	public ResultBody(BaseErrorInfoInterface errorInfo) {
		this.code = errorInfo.getResultCode();
		this.msg = errorInfo.getResultMsg();
	}

	/**
	 * 成功
	 * 
	 * @return
	 */
	public static ResultBody success() {
		return success(CommonEnum.SUCCESS.getResultMsg());
	}
	
	public static ResultBody success(String msg) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.SUCCESS.getResultCode());
		rb.setMsg(msg);
		rb.setData(null);
		return rb;
	}

	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static ResultBody success(Object data) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.SUCCESS.getResultCode());
		rb.setMsg(CommonEnum.SUCCESS.getResultMsg());
		rb.setData(data);
		return rb;
	}
	
	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static ResultBody success(Object data,long total) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.SUCCESS.getResultCode());
		rb.setMsg(CommonEnum.SUCCESS.getResultMsg());
		rb.setData(data);
		rb.setCount(total);
		return rb;
	}
	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static ResultBody success(String msg,Object data,long total) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.SUCCESS.getResultCode());
		rb.setMsg(msg);
		rb.setData(data);
		rb.setCount(total);
		return rb;
	}

	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static ResultBody success(String msg,Object data) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.SUCCESS.getResultCode());
		rb.setMsg(msg);
		rb.setData(data);
		return rb;
	}
	/**
	 * 失败
	 */
	public static ResultBody error(BaseErrorInfoInterface errorInfo) {
		ResultBody rb = new ResultBody();
		rb.setCode(errorInfo.getResultCode());
		rb.setMsg(errorInfo.getResultMsg());
		rb.setData(null);
		return rb;
	}

	/**
	 * 异常
	 */
	public static ResultBody error(String code, String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(code);
		rb.setMsg(message);
		rb.setData(null);
		return rb;
	}
	
	/**
	 * 异常
	 */
	public static ResultBody error( String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.ERROR.getResultCode());
		rb.setMsg(message);
		rb.setData(null);
		return rb;
	}
	

	/**
	 * 失败
	 */
	public static ResultBody failure(String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.FALSE.getResultCode());
		rb.setMsg(message);
		rb.setData(null);
		return rb;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	public Map<String, Object> toMap() {
		return JsonUtil.entityToMap(this);
	}
	
	public JSONObject toJson() {
		return (JSONObject) JSONObject.toJSON(this);
	}
}
