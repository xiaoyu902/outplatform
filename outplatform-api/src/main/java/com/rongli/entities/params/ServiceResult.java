package com.rongli.entities.params;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ServiceResult {

	@ApiModelProperty("返回结果")
	private Boolean result;
	
	@ApiModelProperty("返回说明")
	private String msg;
	
	@ApiModelProperty("接收的第三方原始json数据")
	private String fromThirdData;
	
	@ApiModelProperty("接收转化之前的his数据")
	private String fromHisData;
	
	@ApiModelProperty("通过原始json数据转化后返回给his的最终数据")
	private String toHisData;
	
	@ApiModelProperty("返回给第三方的最终数据")
	private String toThirdData;
	
	@ApiModelProperty("存数据库数据")
	private JSONObject dbData;
}
