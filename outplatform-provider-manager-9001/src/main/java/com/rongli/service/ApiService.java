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
import com.rongli.mapper.primary.PatientMapper;

@Service
public class ApiService {

	@Autowired
	private PatientMapper patientMapper;
	
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
	
}
