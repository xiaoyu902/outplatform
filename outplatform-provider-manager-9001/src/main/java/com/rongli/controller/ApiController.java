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
	public Object selectPatientList(Integer page, Integer limit,
			String name, String termId,
			String datetype, String startDate, String endDate) {
		return apiService.selectPatientList(page, limit,
				name, termId,
				datetype, startDate, endDate);
	}

	@RequiresPermissions("selectPayList")
	@RequestMapping("/selectPayList")
	public Object selectPayList(Integer page, Integer limit,
			String name, String termId, String orderId, String billId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		return apiService.selectPayList(page, limit,
				name, termId, orderId, billId, transactionNo, bankCardNo, channelType,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("selectRechargeList")
	@RequestMapping("/selectRechargeList")
	public Object selectRechargeList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		return apiService.selectRechargeList(page, limit,
				name, termId, orderId, transactionNo, bankCardNo, channelType,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("selectRegisterList")
	@RequestMapping("/selectRegisterList")
	public Object selectRegisterList(Integer page, Integer limit,
			String name, String termId, String orderId, String hospOrderId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		return apiService.selectRegisterList(page, limit,
				name, termId, orderId, hospOrderId, transactionNo, bankCardNo, channelType,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("payConsole")
	@RequestMapping("/payConsole")
	public Object payConsole(Integer page, Integer limit, String datetype, String startDate, String endDate) {
		return apiService.payConsole(page, limit, datetype, startDate, endDate);
	}
}
