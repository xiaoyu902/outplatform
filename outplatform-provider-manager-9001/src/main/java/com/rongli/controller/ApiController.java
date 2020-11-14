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
			String name, String termId, String patientId, String cardType, String tradeResult,
			String datetype, String startDate, String endDate) {
		return apiService.selectPatientList(page, limit,
				name, termId, patientId, cardType, tradeResult,
				datetype, startDate, endDate);
	}

	@RequiresPermissions("selectPayList")
	@RequestMapping("/selectPayList")
	public Object selectPayList(Integer page, Integer limit,
			String name, String termId, String orderId, String billId, String transactionNo, String bankCardNo, String totalFee,
			String channelType, String payType, String cardType, String tradeResult,
			String datetype, String startDate, String endDate) {
		return apiService.selectPayList(page, limit,
				name, termId, orderId, billId, transactionNo, bankCardNo, totalFee,
				channelType, payType, cardType, tradeResult,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("selectRechargeList")
	@RequestMapping("/selectRechargeList")
	public Object selectRechargeList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String amount,
			String channelType, String tradeResult,
			String datetype, String startDate, String endDate) {
		return apiService.selectRechargeList(page, limit,
				name, termId, orderId, transactionNo, bankCardNo, amount,
				channelType, tradeResult,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("selectRegisterList")
	@RequestMapping("/selectRegisterList")
	public Object selectRegisterList(Integer page, Integer limit,
			String name, String termId, String orderId, String hospOrderId, String transactionNo, String bankCardNo, String totalFee,
			String departmentId, String doctorId,
			String channelType, String payType, String cardType, String tradeResult,
			String datetype, String startDate, String endDate) {
		return apiService.selectRegisterList(page, limit,
				name, termId, orderId, hospOrderId, transactionNo, bankCardNo, totalFee,
				departmentId, doctorId,
				channelType, payType, cardType, tradeResult,
				datetype, startDate, endDate);
	}
	
	@RequiresPermissions("payConsole")
	@RequestMapping("/payConsole")
	public Object payConsole(String datetype, String startDate, String endDate) {
		return apiService.payConsole(datetype, startDate, endDate);
	}
	
	@RequiresPermissions("rechargeConsole")
	@RequestMapping("/rechargeConsole")
	public Object rechargeConsole(String datetype, String startDate, String endDate) {
		return apiService.rechargeConsole(datetype, startDate, endDate);
	}
	

	@RequiresPermissions("registerConsole")
	@RequestMapping("/registerConsole")
	public Object registerConsole(String departmentId, String doctorId, String datetype, String startDate, String endDate) {
		return apiService.registerConsole(departmentId, doctorId, datetype, startDate, endDate);
	}
	
	@RequiresPermissions("selectDeptAndDockerList")
	@RequestMapping("/selectDeptAndDockerList")
	public Object selectDeptAndDockerList() {
		return apiService.selectDeptAndDockerList();
	}
}
