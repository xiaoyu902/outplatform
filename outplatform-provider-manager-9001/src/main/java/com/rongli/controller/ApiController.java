package com.rongli.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rongli.service.ApiService;

@RestController
@RequestMapping("/trade")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@RequiresPermissions("selectPatientList")
	@RequestMapping("/selectPatientList")
	public Object selectPatientList(Integer page, Integer limit, String name, String termId) {
		return apiService.selectPatientList(page, limit, name, termId);
	}
}
