package com.rongli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongli.entities.Menu;
import com.rongli.entities.ResultBody;
import com.rongli.mapper.primary.BaseMapper;

@Service
public class OrgService {
	
	@Autowired
	private BaseMapper baseMapper;

	
	public Object selectMenuList() {
		List<Menu> list=baseMapper.selectMenuList();
		return ResultBody.success(list);
	}
}
