package com.rongli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.Patient;
import com.rongli.entities.params.PayEntity;
import com.rongli.entities.params.Recharge;
import com.rongli.entities.params.Register;
import com.rongli.mapper.primary.PatientMapper;
import com.rongli.mapper.primary.PayMapper;
import com.rongli.mapper.primary.RechargeMapper;
import com.rongli.mapper.primary.RegisterMapper;

@Service
public class ApiService {

	@Autowired
	private PatientMapper patientMapper;
	
	@Autowired
	private PayMapper payMapper;
	
	@Autowired
	private RechargeMapper rechargeMapper;
	
	@Autowired
	private RegisterMapper registerMapper;
	
	/**
	 * 查询用户注册列表
	 * @param page
	 * @param limit
	 * @param name
	 * @param termId
	 * @return
	 */
	public Object selectPatientList(Integer page, Integer limit, String name, String termId) {
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		PageHelper.startPage(page, limit);
		QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(!StringUtil.isEmpty(name), "name", name);
		queryWrapper.like(!StringUtil.isEmpty(termId), "term_id", termId);
		List<Patient> patientList = patientMapper.selectList(queryWrapper);
	
		PageInfo<Patient> pageInfo = new PageInfo<>(patientList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
	
	/**
	 * 查询支付列表
	 * @param page
	 * @param limit
	 * @param name
	 * @param termId
	 * @param orderId
	 * @param transactionNo
	 * @param bankCardNo
	 * @return
	 */
	public Object selectPayList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		
		PageHelper.startPage(page, limit);
		QueryWrapper<PayEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(!StringUtil.isEmpty(name), "name", name);
		queryWrapper.like(!StringUtil.isEmpty(termId), "term_id", termId);
		queryWrapper.like(!StringUtil.isEmpty(orderId), "order_id", orderId);
		queryWrapper.like(!StringUtil.isEmpty(transactionNo), "transaction_no", transactionNo);
		queryWrapper.like(!StringUtil.isEmpty(bankCardNo), "bank_card_no", bankCardNo);
		List<PayEntity> payList = payMapper.selectList(queryWrapper);
		
		PageInfo<PayEntity> pageInfo = new PageInfo<>(payList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
	
	/**
	 * 查询充值列表
	 * @param page
	 * @param limit
	 * @param name
	 * @param termId
	 * @param orderId
	 * @param transactionNo
	 * @param bankCardNo
	 * @return
	 */
	public Object selectRechargeList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		
		PageHelper.startPage(page, limit);
		QueryWrapper<Recharge> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(!StringUtil.isEmpty(name), "name", name);
		queryWrapper.like(!StringUtil.isEmpty(termId), "term_id", termId);
		queryWrapper.like(!StringUtil.isEmpty(orderId), "order_id", orderId);
		queryWrapper.like(!StringUtil.isEmpty(transactionNo), "transaction_no", transactionNo);
		queryWrapper.like(!StringUtil.isEmpty(bankCardNo), "bank_card_no", bankCardNo);
		List<Recharge> rechargeList = rechargeMapper.selectList(queryWrapper);
		
		PageInfo<Recharge> pageInfo = new PageInfo<>(rechargeList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
	
	/**
	 * 查询挂号列表
	 * @param page
	 * @param limit
	 * @param name
	 * @param termId
	 * @param orderId
	 * @param transactionNo
	 * @param bankCardNo
	 * @return
	 */
	public Object selectRegisterList(Integer page, Integer limit,
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String hospOrderId) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		
		PageHelper.startPage(page, limit);
		QueryWrapper<Register> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(!StringUtil.isEmpty(name), "name", name);
		queryWrapper.like(!StringUtil.isEmpty(termId), "term_id", termId);
		queryWrapper.like(!StringUtil.isEmpty(orderId), "order_id", orderId);
		queryWrapper.like(!StringUtil.isEmpty(transactionNo), "transaction_no", transactionNo);
		queryWrapper.like(!StringUtil.isEmpty(bankCardNo), "bank_card_no", bankCardNo);
		queryWrapper.like(!StringUtil.isEmpty(hospOrderId), "hosp_order_id", hospOrderId);
		List<Register> registerList = registerMapper.selectList(queryWrapper);
		
		PageInfo<Register> pageInfo = new PageInfo<>(registerList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
}
