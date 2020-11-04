package com.rongli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rongli.service.OrgService;

@RestController
@RequestMapping(value="org",method = {RequestMethod.POST,RequestMethod.GET})
public class OrgController {

	@Autowired
	private OrgService orgService;
	

	
	@RequestMapping(value="selectMenuList")
	public Object selectMenuList(){
		return orgService.selectMenuList();
	}
	
	
}
