package com.rongli.entities;

import java.io.Serializable;

import com.rongli.common.util.StringUtil;
import com.rongli.entities.enums.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Term implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String termid,orgno,mchno,channelno,termname,address,remark,isactive;
	private String orgname,mchname,channelname;
	
	public boolean isActive() {
		if(isactive==null)
			return false;
		else
			return StringUtil.compare(isactive, ActiveEnum.isActive.getCode());			
	}
	
	
}
