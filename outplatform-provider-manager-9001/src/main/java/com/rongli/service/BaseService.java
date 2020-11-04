package com.rongli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.rongli.common.exception.BaseException;
import com.rongli.entities.Menu;
import com.rongli.entities.Permission;
import com.rongli.entities.ResultBody;
import com.rongli.mapper.primary.BaseMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BaseService {

	@Autowired
	private BaseMapper baseMapper;
	
	public Object selectMenuList() {
		List<Menu> list=baseMapper.selectAllMenuList();
		JSONObject obj=new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("total",list.size());
		obj.put("data",list);
		return obj;
	}
	
	@Transactional()
	public Object updateMenu(Menu menu) {
		if(menu==null) {
			throw new BaseException("参数为空");
		}else if(StringUtils.isEmpty(menu.getMenuid())) {
			throw new BaseException("菜单id为空");
		}
	  
		if(StringUtils.isEmpty(menu.getMenuname())) {
			throw new BaseException("菜单名为空");
		}else if(StringUtils.isEmpty(menu.getIsinside())) {
			throw new BaseException("是否内部跳转菜单为空");
		}
		
		int row=baseMapper.updateMenu(menu);
		if(row!=1) {
			throw new BaseException("修改菜单失败");
		}

		return ResultBody.success("修改成功!");
	}
	
	@Transactional()
	public Object addMenu(Menu menu) {
		if(menu==null) {
			throw new BaseException("参数为空");
		}else if(StringUtils.isEmpty(menu.getMenuid())) {
			throw new BaseException("菜单id为空");
		}
		
		if(StringUtils.isEmpty(menu.getMenuname())) {
			throw new BaseException("菜单名为空");
		}else if(StringUtils.isEmpty(menu.getIsinside())) {
			throw new BaseException("是否内部跳转菜单为空");
		}
		
		int row=baseMapper.addMenu(menu);
		if(row!=1) {
			throw new BaseException("添加菜单失败");
		}

		return ResultBody.success("添加菜单成功!");

	}
	
	@Transactional()
	public Object delMenuByMenuId(String menuid) {	
		if(StringUtils.isEmpty(menuid)) {
			throw new BaseException("菜单id为空");
		}
		if(menuid.compareTo("0")==0) {
			throw new BaseException("首页不允许删除");
		}

		if(baseMapper.delMenuByMenuId(menuid)==1) {
			return ResultBody.success();
		}else {
			throw new BaseException("删除菜单失败row!=1");
		}
	}
	

	
	public Object selectPermissionList(String menuid) {
		if(StringUtils.isEmpty(menuid))
			throw new BaseException("菜单id为空");
		return ResultBody.success(baseMapper.selectPermissionList(menuid));
	}
	
	public Object addPermission(Permission permission) {
		if(StringUtils.isEmpty(permission.getPerid()))
			throw new BaseException("权限id为空");
		if(StringUtils.isEmpty(permission.getPermission()))
			throw new BaseException("权限串为空");
		if(StringUtils.isEmpty(permission.getPermissiondesc()))
			throw new BaseException("权限描述为空");
		if(StringUtils.isEmpty(permission.getMenuid()))
			throw new BaseException("菜单id为空");
		
		if(baseMapper.selectPermission(permission.getPerid(), permission.getPermission())!=null)
			throw new BaseException("已存在的权限id或权限串");
		
		int row = baseMapper.addPermission(permission);
		if(row > 0) {
			return ResultBody.success();
		}else {
			return ResultBody.failure("添加失败");
		}
	}
	
	public Object updatePermission(Permission permission) {
		if(StringUtils.isEmpty(permission.getPerid()))
			throw new BaseException("权限id为空");
		if(StringUtils.isEmpty(permission.getPermission()))
			throw new BaseException("权限串为空");
		if(StringUtils.isEmpty(permission.getPermissiondesc()))
			throw new BaseException("权限描述为空");

		int row = baseMapper.updatePermission(permission);
		if(row > 0) {
			return ResultBody.success();
		}else {
			return ResultBody.failure("添加失败");
		}
	}
	
	public Object deletePermission(String perid) {
		if(StringUtils.isEmpty(perid))
			throw new BaseException("权限id为空");
		int row = baseMapper.deletePermission(perid);
		if(row > 0) {
			return ResultBody.success();
		}else {
			return ResultBody.failure("删除失败");
		}
	}

}
