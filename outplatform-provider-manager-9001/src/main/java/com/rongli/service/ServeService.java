package com.rongli.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.rongli.mapper.primary.DictMapper;
import com.rongli.mapper.primary.ServeMapper;


@Service
public class ServeService {

	@Autowired
	private ServeMapper serveMapper;
	
	@Autowired
	private DictMapper dictMapper;
	

	/**
	 * 分页查询服务
	 * @param page
	 * @param limit
	 * @param serviceId
	 * @param explain
	 * @return
	 */
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
	
	/**
	 * 修改服务
	 * @param serviceEntity
	 */
	public void updateById(ServiceEntity serviceEntity) {
		serveMapper.updateById(serviceEntity);
	}

	/**
	 * 保存服务
	 * @param serviceEntity
	 */
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
		if(StringUtil.isEmpty(serviceEntity.getIsvalid())) {
			serviceEntity.setIsvalid("0");
		}
		ServiceEntity service = serveMapper.selectById(serviceEntity.getServiceId());
		if(service == null) {
			new BaseException("服务ID重复！");
		}
		
		serveMapper.insert(serviceEntity);
		
	}
	
	/**
	 * 删除服务
	 * @param serviceId
	 */
	public void deleteById(String serviceId) {
		if(StringUtil.isEmpty(serviceId)) {
			new BaseException("serviceId为空");
		}
		serveMapper.deleteById(serviceId);
	}

	/**
	 * 查询所有api表
	 * @return
	 */
	public List<String> findTableList() {
		return serveMapper.findTableList();
	}

	/**
	 * 根据表名查询字段列表
	 * @param tableName
	 * @return
	 */
	public List<JSONObject> findColumnListByTable(String tableName) {
		return serveMapper.findColumnListByTable(tableName);
	}
	

	/**
	 * 字典转换列表
	 * @return
	 */
	public List<JSONObject> selectDictList() {
		return dictMapper.selectDictList();
	}
	
	/**
	 * 获取接口校验列表
	 * @return
	 */
	public List<JSONObject> selectCheckList() {
		List<JSONObject> list = new ArrayList<>();
		Method[] methods = ParamCheckService.class.getDeclaredMethods();
		JSONObject obj;
		for (int i = 0; i < methods.length; i++) {
			obj = new JSONObject();
			obj.put("name", methods[i].getName());
			list.add(obj);
		}
		return list;
	}
	
}
