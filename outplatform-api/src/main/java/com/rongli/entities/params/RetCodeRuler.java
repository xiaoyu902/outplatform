package com.rongli.entities.params;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description= "输出参数返回码规则")
public class RetCodeRuler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "his编码规则")
	public String hiscode;
	
	@ApiModelProperty(value = "his返回说明规则")
	public String hismsg;
	
	@ApiModelProperty(value = "正确的返回码序列")
	public String rightcode;
}
