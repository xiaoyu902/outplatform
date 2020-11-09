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

	@RequiresPermissions("selectPayList")
	@RequestMapping("/selectPayList")
	public Object selectPayList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo) {
		return apiService.selectPayList(page, limit, name, termId, orderId, transactionNo, bankCardNo);
	}
	
	@RequiresPermissions("selectRechargeList")
	@RequestMapping("/selectRechargeList")
	public Object selectRechargeList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo) {
		return apiService.selectRechargeList(page, limit, name, termId, orderId, transactionNo, bankCardNo);
	}
	
	@RequiresPermissions("selectRegisterList")
	@RequestMapping("/selectRegisterList")
	public Object selectRegisterList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String hospOrderId) {
		return apiService.selectRegisterList(page, limit, name, termId, orderId, transactionNo, bankCardNo, hospOrderId);
	}
}
