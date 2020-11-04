package com.rongli.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String perid,permission,permissiondesc,menuid;
}
