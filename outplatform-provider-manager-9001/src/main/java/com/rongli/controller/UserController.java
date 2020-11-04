package com.rongli.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rongli.entities.Menu;
import com.alibaba.fastjson.JSONArray;
import com.rongli.entities.Role;
import com.rongli.entities.User;
import com.rongli.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequiresPermissions("selectUserList")
	@RequestMapping(value="trade/selectUserList")
	public Object selectUserList(String account,Integer page,Integer limit){
		return userService.selectUserList(account,page, limit);
	}
	
	@RequiresPermissions("addUser")
	@RequestMapping(value="trade/addUser")
	public Object addUser(User user,String rolestr){
		return userService.addUser(user, rolestr);
	}
	
	@RequiresPermissions("editUser")
	@RequestMapping(value="trade/editUser")
	public Object editUser(User user,String rolestr){
		return userService.updateUser(user, rolestr);
	}
	
	@RequiresPermissions("editUserPsw")
	@RequestMapping(value="trade/editUserPsw")
	public Object editUserPsw(User user){
		return userService.updateUserPsw(user);
	}
	
	
	@RequiresPermissions("delUser")
	@RequestMapping(value="trade/delUser")
	public Object delUser(String userid){
		return userService.delUser(userid);
	}
	
	@RequiresPermissions("selectRoleList")
	@RequestMapping(value="trade/selectRoleList")
	public Object selectRoleList(Integer page,Integer limit){
		return userService.selectRoleList(page, limit);
	}
	
	@RequiresPermissions("delRole")
	@RequestMapping(value="trade/delRole")
	public Object delRole(String roleid){
		return userService.delRole(roleid);
	}
	
	@RequiresPermissions("addRole")
	@RequestMapping(value="trade/addRole")
	public Object addRole(Role role){
		return userService.addRole(role);
	}
	
	@RequiresPermissions("editRole")
	@RequestMapping(value="trade/editRole")
	public Object editRole(Role role){
		return userService.editRole(role);
	}
	
	@RequiresPermissions("accessManagement")
	@RequestMapping(value="trade/accessManagement")
	public Object accessManagement(String roleid,@RequestBody Object data){
		return userService.updatePermission(roleid, JSONArray.parseArray(data.toString()));
	}
}
