package com.rongli.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rongli.entities.Menu;
import com.rongli.entities.Permission;
import com.rongli.service.BaseService;

@RestController
@RequestMapping(value="trade",method = {RequestMethod.POST})
public class BaseController {

	@Autowired
	BaseService baseService;
	
	@RequiresPermissions("selectMenuList")
	@RequestMapping(value="selectMenuList")
	public Object selectMenuList(){
		return baseService.selectMenuList();
	}
	
	@RequiresPermissions("editMenu")
	@RequestMapping(value="editMenu")
	public Object editMenu(Menu menu){
		return baseService.updateMenu(menu);
	}

	@RequiresPermissions("addMenu")
	@RequestMapping(value="addMenu")
	public Object addMenu(Menu menu){
		return baseService.addMenu(menu);
	}
	
	@RequiresPermissions("delMenuByMenuId")
	@RequestMapping(value="delMenuByMenuId")
	public Object delMenuByMenuId(String menuid){
		return baseService.delMenuByMenuId(menuid);
	}
	@RequiresPermissions("permissionList")
	@RequestMapping(value="permissionList")
	public Object selectPermissionList(String menuid) {
		return baseService.selectPermissionList(menuid);
	}
	@RequiresPermissions("addPermission")
	@RequestMapping(value="addPermission")
	public Object addPermission(Permission permission) {
		return baseService.addPermission(permission);
	}
	@RequiresPermissions("editPermission")
	@RequestMapping(value="editPermission")
	public Object editPermission(Permission permission) {
		return baseService.updatePermission(permission);
	}
	@RequiresPermissions("delPermission")
	@RequestMapping(value="delPermission")
	public Object delPermission(String perid) {
		return baseService.deletePermission(perid);
	}
	
}
