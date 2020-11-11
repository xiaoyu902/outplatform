package com.rongli.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongli.common.util.StringUtil;
import com.rongli.common.exception.BaseException;
import com.rongli.common.util.DateUtil;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.PayEntity;
import com.rongli.entities.params.Recharge;
import com.rongli.entities.params.Register;
import com.rongli.mapper.primary.DictMapper;
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
	
	@Autowired
	private DictMapper dictMapper;
	
	/**
	 * 查询用户注册列表
	 * @param page
	 * @param limit
	 * @param name
	 * @param termId
	 * @return
	 * @throws ParseException 
	 */
	public Object selectPatientList(Integer page, Integer limit, // 分页
			String name, String termId, // 模糊查询
			String datetype, String startDate, String endDate){ // 时间范围
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			if(StringUtil.compare("y", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				startDate = DateUtil.getYearFirst(Integer.parseInt(startDate));
				endDate = DateUtil.getYearLast(Integer.parseInt(endDate));
			}else if(StringUtil.compare("m", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				List<String> datelist = DateUtil.getMonthFullDay(startDate);
				startDate = datelist.get(0);
				endDate = datelist.get(datelist.size()-1);
			}
			startDate += " 00:00:00";
			endDate += " 23:59:59";
		}

		PageHelper.startPage(page, limit);
		List<JSONObject> patientList = patientMapper.selectPatientList(name, termId, startDate, endDate);
	
		PageInfo<JSONObject> pageInfo = new PageInfo<>(patientList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
	
	/**
	 * 查询缴费列表
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
			String name, String termId, String orderId, String billId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			if(StringUtil.compare("y", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				startDate = DateUtil.getYearFirst(Integer.parseInt(startDate));
				endDate = DateUtil.getYearLast(Integer.parseInt(endDate));
			}else if(StringUtil.compare("m", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				List<String> datelist = DateUtil.getMonthFullDay(startDate);
				startDate = datelist.get(0);
				endDate = datelist.get(datelist.size()-1);
			}
			startDate += " 00:00:00";
			endDate += " 23:59:59";
		}
		
		PageHelper.startPage(page, limit);
		List<JSONObject> payList = payMapper.selectPayList(name, termId, orderId, billId, transactionNo, bankCardNo, channelType, datetype, startDate, endDate);

		PageInfo<JSONObject> pageInfo = new PageInfo<>(payList);
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
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			if(StringUtil.compare("y", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				startDate = DateUtil.getYearFirst(Integer.parseInt(startDate));
				endDate = DateUtil.getYearLast(Integer.parseInt(endDate));
			}else if(StringUtil.compare("m", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				List<String> datelist = DateUtil.getMonthFullDay(startDate);
				startDate = datelist.get(0);
				endDate = datelist.get(datelist.size()-1);
			}
			startDate += " 00:00:00";
			endDate += " 23:59:59";
		}
		
		PageHelper.startPage(page, limit);
		List<JSONObject> rechargeList = rechargeMapper.selectRechargeList(name, termId, orderId, transactionNo, bankCardNo, channelType, datetype, startDate, endDate);
		
		PageInfo<JSONObject> pageInfo = new PageInfo<>(rechargeList);
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
			String name, String termId, String orderId, String hospOrderId, String transactionNo, String bankCardNo, String channelType,
			String datetype, String startDate, String endDate) {
		
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			if(StringUtil.compare("y", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				startDate = DateUtil.getYearFirst(Integer.parseInt(startDate));
				endDate = DateUtil.getYearLast(Integer.parseInt(endDate));
			}else if(StringUtil.compare("m", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				List<String> datelist = DateUtil.getMonthFullDay(startDate);
				startDate = datelist.get(0);
				endDate = datelist.get(datelist.size()-1);
			}
			startDate += " 00:00:00";
			endDate += " 23:59:59";
		}
		
		PageHelper.startPage(page, limit);
		List<JSONObject> registerList = registerMapper.selectRegisterList(name, termId, orderId, hospOrderId, transactionNo, bankCardNo, channelType, datetype, startDate, endDate);
		
		PageInfo<JSONObject> pageInfo = new PageInfo<>(registerList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		return obj;
	}
	
	/**
	 * 支付渠道
	 * @return
	 */
	public List<JSONObject> selectChannelList() {
		return dictMapper.selectChannelList();
	}

	/**
	 * 缴费统计
	 * @param datetype
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Object payConsole(Integer page, Integer limit, String datetype, String startDate, String endDate) {

		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			if(StringUtil.compare("y", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				startDate = DateUtil.getYearFirst(Integer.parseInt(startDate));
				endDate = DateUtil.getYearLast(Integer.parseInt(endDate));
			}else if(StringUtil.compare("m", datetype)) {
				if(!StringUtil.compare(startDate, endDate))
					throw new BaseException("日期格式错误");
				List<String> datelist = DateUtil.getMonthFullDay(startDate);
				startDate = datelist.get(0);
				endDate = datelist.get(datelist.size()-1);
			}
			startDate += " 00:00:00";
			endDate += " 23:59:59";
		}
		
		PageHelper.startPage(page, limit);
		List<JSONObject> payList = payMapper.selectPayList(null, null, null, null, null, null, null, datetype, startDate, endDate);
		
		PageInfo<JSONObject> pageInfo = new PageInfo<>(payList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		
		List<JSONObject> lineList = payMapper.selectCountAndSumByDateAndChannel(datetype, startDate, endDate);
		JSONObject lineObj = new JSONObject();
		lineObj.put("data", lineList);
		List<String> dateList = new ArrayList<>();
		for (JSONObject pay: payList) {
			String tradeTime = pay.getString("tradeTime");
			dateList.add(tradeTime);
		}
		lineObj.put("xAxis", dateList);
		
		obj.put("line", lineObj);
		obj.put("pie", payMapper.selectCountAndSumByChannel(datetype, startDate, endDate));
		
		return obj;
	}
	
}
