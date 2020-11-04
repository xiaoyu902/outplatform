package com.rongli.entities;
import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.rongli.common.utils.JsonUtil;
import com.rongli.entities.enums.CommonEnum;
import com.rongli.exception.BaseErrorInfoInterface;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回串")
public class ResultBody implements Serializable{

	/**
	 * 响应代码
	 */
	@ApiModelProperty("返回代码")
	private String code;

	/**
	 * 响应消息
	 */
	@ApiModelProperty("返回内容")
	private String msg;
	/**
	 * 响应结果
	 */
//	@ApiModelProperty("返回拓展数据_data")
//	private Object data;
//	
//	@ApiModelProperty("返回拓展数据_count")
//	private Long count;
//	
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
		return rb;
	}

	public static ResultBody error(BaseErrorInfoInterface errorInfo) {
		ResultBody rb = new ResultBody();
		rb.setCode(errorInfo.getResultCode());
		rb.setMsg(errorInfo.getResultMsg());
		return rb;
	}

	/**
	 * 异常
	 */
	public static ResultBody error(String code, String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(code);
		rb.setMsg(message);
		return rb;
	}
	
	/**
	 * 异常
	 */
	public static ResultBody error( String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.ERROR.getResultCode());
		rb.setMsg(message);
		return rb;
	}
	

	/**
	 * 失败
	 */
	public static ResultBody failure(String message) {
		ResultBody rb = new ResultBody();
		rb.setCode(CommonEnum.FALSE.getResultCode());
		rb.setMsg(message);
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
