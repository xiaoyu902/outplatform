package com.rongli.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sort;
	private String roleid,menuid,fmenuid,menuname,fontlogo,path,isinside,linkurl;
	private List<Permission> permissionList;
	private List<Menu> sonMenuList;
	

}
