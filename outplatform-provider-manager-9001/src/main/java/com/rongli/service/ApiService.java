package com.rongli.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
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
	public Object selectPatientList(Integer page, Integer limit,
			String name, String termId, String patientId, String cardType, String tradeResult,
			String datetype, String startDate, String endDate){
		
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
		List<JSONObject> patientList = patientMapper.selectPatientList(name, termId, patientId, cardType, tradeResult, startDate, endDate);
	
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
			String name, String termId, String orderId, String billId, String transactionNo, String bankCardNo, String totalFee,
			String channelType, String payType, String cardType, String tradeResult,
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
		List<JSONObject> payList = payMapper.selectPayList(name, termId, orderId, billId, transactionNo, bankCardNo, totalFee,
				channelType, payType, cardType, tradeResult,
				datetype, startDate, endDate);

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
			String name, String termId, String orderId, String transactionNo, String bankCardNo, String amount,
			String channelType, String tradeResult,
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
		List<JSONObject> rechargeList = rechargeMapper.selectRechargeList(name, termId, orderId, transactionNo, bankCardNo, amount,
				channelType, tradeResult,
				datetype, startDate, endDate);
		
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
			String name, String termId, String orderId, String hospOrderId, String transactionNo, String bankCardNo, String totalFee,
			String departmentId, String doctorId,
			String channelType, String payType, String cardType, String tradeResult,
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
		List<JSONObject> registerList = registerMapper.selectRegisterList(name, termId, orderId, hospOrderId, transactionNo, bankCardNo, totalFee,
				departmentId, doctorId,
				channelType, payType, cardType, tradeResult,
				datetype, startDate, endDate);
		
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
	 * 卡类型
	 * @return
	 */
	public List<JSONObject> selectCardTypeList() {
		return dictMapper.selectCardTypeList();
	}
	
	/**
	 * 支付类型
	 * @return
	 */
	public List<JSONObject> selectPayTypeList() {
		return dictMapper.selectPayTypeList();
	}
	
	/**
	 * 缴费统计
	 * @param datetype
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Object payConsole(String datetype, String startDate, String endDate) {

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
		// 获取缴费列表
		List<JSONObject> payList = payMapper.selectPayList(null, null, null, null, null, null, null, null, null, null, "0", datetype, startDate, endDate);

		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("data", payList);
		
		// 获取统计数据
		List<JSONObject> statList = payMapper.selectCountAndSumByDateAndChannel(datetype, startDate, endDate);
		JSONObject lineObj = new JSONObject();
		// 从缴费列表中获取已存支付渠道、时间集合
		Set<String> channelList = new HashSet<String>(); // 渠道集合
		List<String> dateList = new ArrayList<>(); // x轴时间集合
		for(JSONObject pay : payList) {
			channelList.add(pay.getString("channelTypeDesc"));
			String tradeTime = pay.getString("tradeTime");
			if(datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")-3))) {
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" ")-3);
				dateList.add(tradeTime);
			}else if(!datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")))){
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" "));
				dateList.add(tradeTime);
			}
		}
		// 获取折线集合
		List<JSONObject> lineList = new ArrayList<>(); // 折线集合
		for(String channel : channelList) { // 根据渠道
			JSONObject one = new JSONObject();
			List<Integer> countList = new ArrayList<>();
			List<BigDecimal> sumList = new ArrayList<>();
			for (String date: dateList) { // 根据时间
				Boolean isEmpty = true; // 该时间段是否没有交易数据
				for (JSONObject line: statList) { // 获取 sum、count集合
					if(channel.equals(line.get("channelTypeDesc")) && date.equals(line.get("tradeTime"))) {
						countList.add(Integer.parseInt(line.getString("count")));
						sumList.add(new BigDecimal(line.getString("sum")));
						isEmpty = false; // 有交易数据
						break;
					}
				}
				// 没有交易数据就赋值0
				if(isEmpty) {
					countList.add(0);
					sumList.add(new BigDecimal(0));
				}
			}
			one.put("channelTypeDesc", channel);
			one.put("countList", countList);
			one.put("sumList", sumList);
			lineList.add(one);
		}
		// line
		lineObj.put("data", lineList);
		lineObj.put("xAxis", dateList);
		obj.put("line", lineObj);
		// pie
		obj.put("pie", payMapper.selectCountAndSumByChannel(datetype, startDate, endDate));
		
		return obj;
	}
	
	/**
	 * 充值统计
	 * @param datetype
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Object rechargeConsole(String datetype, String startDate, String endDate) {

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
		// 获取缴费列表
		List<JSONObject> payList = rechargeMapper.selectRechargeList(null, null, null, null, null, null, null, "0", datetype, startDate, endDate);

		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("data", payList);
		
		// 获取统计数据
		List<JSONObject> statList = rechargeMapper.selectCountAndSumByDateAndChannel(datetype, startDate, endDate);
		JSONObject lineObj = new JSONObject();
		// 从缴费列表中获取已存支付渠道、时间集合
		Set<String> channelList = new HashSet<String>(); // 渠道集合
		List<String> dateList = new ArrayList<>(); // x轴时间集合
		for(JSONObject pay : payList) {
			channelList.add(pay.getString("channelTypeDesc"));
			String tradeTime = pay.getString("tradeTime");
			if(datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")-3))) {
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" ")-3);
				dateList.add(tradeTime);
			}else if(!datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")))){
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" "));
				dateList.add(tradeTime);
			}
		}
		// 获取折线集合
		List<JSONObject> lineList = new ArrayList<>(); // 折线集合
		for(String channel : channelList) { // 根据渠道
			JSONObject one = new JSONObject();
			List<Integer> countList = new ArrayList<>();
			List<BigDecimal> sumList = new ArrayList<>();
			for (String date: dateList) { // 根据时间
				Boolean isEmpty = true; // 该时间段是否没有交易数据
				for (JSONObject line: statList) { // 获取 sum、count集合
					if(channel.equals(line.get("channelTypeDesc")) && date.equals(line.get("tradeTime"))) {
						countList.add(Integer.parseInt(line.getString("count")));
						sumList.add(new BigDecimal(line.getString("sum")));
						isEmpty = false; // 有交易数据
						break;
					}
				}
				// 没有交易数据就赋值0
				if(isEmpty) {
					countList.add(0);
					sumList.add(new BigDecimal(0));
				}
			}
			one.put("channelTypeDesc", channel);
			one.put("countList", countList);
			one.put("sumList", sumList);
			lineList.add(one);
		}
		// line
		lineObj.put("data", lineList);
		lineObj.put("xAxis", dateList);
		obj.put("line", lineObj);
		// pie
		obj.put("pie", rechargeMapper.selectCountAndSumByChannel(datetype, startDate, endDate));
		
		return obj;
	}

	/**
	 * 挂号统计
	 * @param deptId
	 * @param doctorId
	 * @param datetype
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Object registerConsole(String deptId, String doctorId, String datetype, String startDate, String endDate) {

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
		// 获取缴费列表
		List<JSONObject> payList = registerMapper.selectRegisterList(null, null, null, null, null, null, null, deptId, doctorId, null, null, null, "0", datetype, startDate, endDate);

		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("data", payList);
		
		// 获取统计数据
		List<JSONObject> statList = registerMapper.selectCountAndSumByDateAndChannel(deptId, doctorId, datetype, startDate, endDate);
		JSONObject lineObj = new JSONObject();
		// 从缴费列表中获取已存支付渠道、时间集合
		Set<String> channelList = new HashSet<String>(); // 渠道集合
		List<String> dateList = new ArrayList<>(); // x轴时间集合
		for(JSONObject pay : payList) {
			channelList.add(pay.getString("channelTypeDesc"));
			String tradeTime = pay.getString("tradeTime");
			if(datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")-3))) {
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" ")-3);
				dateList.add(tradeTime);
			}else if(!datetype.equals("y") && !dateList.contains(tradeTime.substring(0, tradeTime.indexOf(" ")))){
				tradeTime = tradeTime.substring(0, tradeTime.indexOf(" "));
				dateList.add(tradeTime);
			}
		}
		// 获取折线集合
		List<JSONObject> lineList = new ArrayList<>(); // 折线集合
		for(String channel : channelList) { // 根据渠道
			JSONObject one = new JSONObject();
			List<Integer> countList = new ArrayList<>();
			List<BigDecimal> sumList = new ArrayList<>();
			for (String date: dateList) { // 根据时间
				Boolean isEmpty = true; // 该时间段是否没有交易数据
				for (JSONObject line: statList) { // 获取 sum、count集合
					if(channel.equals(line.get("channelTypeDesc")) && date.equals(line.get("tradeTime"))) {
						countList.add(Integer.parseInt(line.getString("count")));
						sumList.add(new BigDecimal(line.getString("sum")));
						isEmpty = false; // 有交易数据
						break;
					}
				}
				// 没有交易数据就赋值0
				if(isEmpty) {
					countList.add(0);
					sumList.add(new BigDecimal(0));
				}
			}
			one.put("channelTypeDesc", channel);
			one.put("countList", countList);
			one.put("sumList", sumList);
			lineList.add(one);
		}
		// line
		lineObj.put("data", lineList);
		lineObj.put("xAxis", dateList);
		obj.put("line", lineObj);
		// pie
		obj.put("pie", registerMapper.selectCountAndSumByChannel(deptId, doctorId, datetype, startDate, endDate));
		
		return obj;
	}
	

	/**
	 * 获取科室、医生列表
	 * @return
	 */
	public Object selectDeptAndDockerList(){
		List<JSONObject> deptList = new ArrayList<>();
		List<JSONObject> list = registerMapper.selectDoctorList();
		// 获取科室列表
		for (JSONObject dept : new ArrayList<>(list)) {
			Boolean isEmpty = true;
			for(JSONObject d : deptList) {
				if(dept.getString("departmentId").equals(d.getString("departmentId"))) {
					isEmpty = false;
				}
			}
			if(isEmpty) {
				JSONObject obj = (JSONObject) dept.clone();
				obj.remove("doctorId");
				obj.remove("doctorName");
				deptList.add(obj);
			}
		}
		// 获取医生列表
		JSONArray doctorContainer = new JSONArray();
		for (JSONObject dept : deptList) {
			JSONArray doctorList = new JSONArray();
			Iterator<JSONObject> iterator = list.iterator();
			while (iterator.hasNext()) {
				JSONObject doctor = iterator.next();
				if(doctor.getString("departmentId").equals(dept.getString("departmentId"))) {
					JSONObject obj = (JSONObject) doctor.clone();
					obj.remove("departmentId");
					obj.remove("departmentName");
					doctorList.add(obj);
					iterator.remove();
				}
			}
			doctorContainer.add(doctorList);
		}
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("deptList", deptList);
		obj.put("doctorList", doctorContainer);
		return obj;
	}
	
}
