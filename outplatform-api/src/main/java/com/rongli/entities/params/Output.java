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
@ApiModel(description= "输出参数")
public class Output implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "返回节点规则")
	public RetCodeRuler retCodeRuler; 
	
	@ApiModelProperty(value = "多记录集根节点")
	public String recordRoot;
	
	@ApiModelProperty(value = "单记录集")
	public List<Code> titles;
	
	@ApiModelProperty(value = "多记录集")
	public List<Code> records;
}
