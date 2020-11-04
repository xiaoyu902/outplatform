package com.rongli.entities;

import java.io.Serializable;
import java.util.List;

import com.rongli.common.util.StringUtil;
import com.rongli.entities.enums.ActiveEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userid,useraccount,password,username,phone,mail,isactive;
	private List<Role> roleList;


	public boolean isActive() {
		if(isactive==null)
			return false;
		else
			return StringUtil.compare(isactive, ActiveEnum.isActive.getCode());		
					
	}
}
