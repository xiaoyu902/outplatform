package com.rongli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.rongli.common.exception.BaseException;
import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.mapper.primary.ServeMapper;


@Service
public class ServeService {

	@Autowired
	private ServeMapper serveMapper;
	

	public Object findByPage(Integer page, Integer limit, String serviceId, String explain) {
		if(page == null || page <= 0) {
			page = 1;
		}
		if(limit == null || limit <= 0) {
			limit = 10;
		}
		
		PageHelper.startPage(page, limit);
		QueryWrapper<ServiceEntity> wrapper = new QueryWrapper<>();
		wrapper.like(!StringUtil.isEmpty(serviceId), "service_id", serviceId);
		wrapper.like(!StringUtil.isEmpty(explain), "service_explain", explain);
		
		List<ServiceEntity> serviceList = serveMapper.selectList(wrapper);
		PageInfo<ServiceEntity> pageInfo = new PageInfo<>(serviceList);
		JSONObject obj = new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("count", pageInfo.getTotal());
		obj.put("data", pageInfo.getList());
		return obj;
	}
	
	public void updateById(ServiceEntity serviceEntity) {
		serveMapper.updateById(serviceEntity);
	}

	public void save(ServiceEntity serviceEntity) {
		
		if(StringUtil.isEmpty(serviceEntity.getServiceId())) {
			new BaseException("serviceId为空");
		}
		if(StringUtil.isEmpty(serviceEntity.getServiceExplain())) {
			new BaseException("explain为空");
		}
		if(StringUtil.isEmpty(serviceEntity.getDataType())) {
			new BaseException("datatype为空");
		}
		if(StringUtil.isEmpty(serviceEntity.getInput())) {
			new BaseException("input为空");
		}
		if(StringUtil.isEmpty(serviceEntity.getOutput())) {
			new BaseException("output为空");
		}

		if(StringUtil.isEmpty(serviceEntity.getSavedb())) {
			serviceEntity.setSavedb("0");
		}
		
		serveMapper.insert(serviceEntity);
		
	}
	
	public void deleteById(String serviceId) {
		if(StringUtil.isEmpty(serviceId)) {
			new BaseException("serviceId为空");
		}
		serveMapper.deleteById(serviceId);
	}

	public List<String> findTableList() {
		return serveMapper.findTableList();
	}

	public List<JSONObject> findColumnListByTable(String tableName) {
		return serveMapper.findColumnListByTable(tableName);
	}
	
}
