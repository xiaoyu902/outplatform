package com.rongli.entities.dic;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PayType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String paytype,paytypedesc,remark;
}
