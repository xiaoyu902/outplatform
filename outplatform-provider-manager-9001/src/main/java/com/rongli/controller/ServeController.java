package com.rongli.controller;



import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rongli.entities.ResultBody;
import com.rongli.entities.params.ServiceEntity;
import com.rongli.service.ServeService;


@RestController
@RequestMapping("/trade")
public class ServeController {

	@Autowired
	private ServeService serveService;
	
	/**
	 * 条件+分页查询
	 * @param page
	 * @param size
	 * @param data
	 * @return
	 */
	@RequiresPermissions("selectServiceList")
	@PostMapping("/selectServiceList")
	public Object findByPage(Integer page, Integer limit, String serviceId, String explain) {
		return serveService.findByPage(page, limit, serviceId, explain);
	}
	
	/**
	 * 根据ID修改服务
	 * @param serviceEntity
	 * @return
	 */
	@RequiresPermissions("updateService")
	@PostMapping("/updateService")
	public ResultBody updateById(@RequestBody ServiceEntity serviceEntity) {
		serveService.updateById(serviceEntity);
		return new ResultBody().success("修改成功");
	}
	
	/**
	 * 保存服务
	 * @param serviceEntity
	 * @return
	 */
	@RequiresPermissions("addService")
	@PostMapping("/addService")
	public ResultBody save(@RequestBody ServiceEntity serviceEntity) {
		serveService.save(serviceEntity);
		return new ResultBody().success("添加成功");
	}
	
	/**
	 * 根据服务ID删除服务
	 * @param serviceId
	 * @return
	 */
	@RequiresPermissions("deleteService")
	@PostMapping("/deleteService")
	public ResultBody deleteById(String serviceId) {
		serveService.deleteById(serviceId);
		return new ResultBody().success("删除成功");
	}
	
	/**
	 * 查找t_api开头的所有表名，排查t_api_refund表
	 * @return
	 */
	@RequiresPermissions("findTableList")
	@PostMapping("/findTableList")
	public ResultBody findTableList() {
		return new ResultBody().success("查询成功", serveService.findTableList());
	}

	@RequiresPermissions("findColumnListByTable")
	@PostMapping("/findColumnListByTable")
	public ResultBody findColumnListByTable(String tableName) {
		return new ResultBody().success("查询成功", serveService.findColumnListByTable(tableName));
	}
}
