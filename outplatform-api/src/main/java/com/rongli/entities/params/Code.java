package com.rongli.entities.params;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

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
@ApiModel(description= "单记录说明")
public class Code implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "节点规则")
	private String codeRuler;
	
	@ApiModelProperty(value = "长度")
	private Integer length;
	
	@ApiModelProperty(value = "说明")
	private String explain;
	
	@ApiModelProperty(value = "转化后的节点规则")
	private String changeRuler;
	
	@ApiModelProperty(value = "记数据库字段")
	private String dbfield;
	
	@ApiModelProperty(value = "是否非空")
	private Boolean notNull;
	
	@ApiModelProperty(value = "默认值")
	private String value;
	
	@ApiModelProperty(value = "转化函数")
	private String function;
}
