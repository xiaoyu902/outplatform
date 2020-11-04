package com.rongli.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongli.common.exception.BaseException;
import com.rongli.common.util.DateUtil;
import com.rongli.common.util.StringUtil;
import com.rongli.config.shiro.ShiroConfig;
import com.rongli.entities.Menu;
import com.rongli.entities.ResultBody;
import com.rongli.entities.Role;
import com.rongli.entities.User;
import com.rongli.mapper.primary.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public Object selectUserList(String account,int page,int limit){
		PageHelper.startPage(page, limit);
		List<User> list=userMapper.selectUserList(account);
		PageInfo<User> pageInfo=new PageInfo<User>(list);
		JSONObject obj=new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("total",pageInfo.getTotal());
		obj.put("data",list);
		return obj;
	}
	
	public User selectUser(String account,String password) {
		User user=userMapper.selectUser(account);
		if(user==null) {
			throw new BaseException("无效的账户");
		}else if(user.getPassword().compareTo(password)!=0) {
			throw new BaseException("密码错误");
		}else if(!user.isActive()) {
			throw new BaseException("账户禁止登录");
		}
		return user;
	}
	
	public Object delUser(String userid) {
		if(StringUtils.isEmpty(userid)) {
			throw new BaseException("用户id为空");
		}
		if(userid.compareTo("1")==0) {
			throw new BaseException("超级用户不允许删除");
		}
		if(userMapper.deleteUser(userid)==1) {
			userMapper.delUserRole(userid);
			return ResultBody.success();
		}else {
			throw new BaseException("删除用户失败row!=1");
		}
	}
	
	public Object updateUserPsw(User user) {
		if(user==null) {
			throw new BaseException("参数为空");
		}else if(StringUtils.isEmpty(user.getUserid())) {
			throw new BaseException("用户id为空");
		}
		if(StringUtils.isEmpty(user.getUseraccount())) {
			throw new BaseException("用户账号为空");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new BaseException("用户密码为空");
		}else {
			String hashAlgorithmName = "MD5";
			Object credentials = user.getPassword();
			Object salt = ByteSource.Util.bytes(user.getUseraccount());;
			int hashIterations = ShiroConfig.HashIterations;
			
			Object result = new SimpleHash(hashAlgorithmName, credentials, salt,hashIterations);
			user.setPassword(String.valueOf(result));
		}
		int row=userMapper.updateUser(user);
		if(row!=1) {
			throw new BaseException("修改用户失败");
		}
		return ResultBody.success();
	}
	
	@Transactional()
	public Object updateUser(User user,String rolestr) {
		if(user==null) {
			throw new BaseException("参数为空");
		}else if(StringUtils.isEmpty(user.getUserid())) {
			throw new BaseException("用户id为空");
		}
		User user_sql=userMapper.selectUser(user.getUseraccount());
		if(user_sql!=null&&(user_sql.getUseraccount()).compareTo(user.getUseraccount())!=0){
			throw new BaseException("已存在的账户");
		}else if(StringUtils.isEmpty(user.getUseraccount())) {
			throw new BaseException("账户号为空");
		}else if(StringUtils.isEmpty(user.getUsername())) {
			throw new BaseException("用户名为空");
		}
		
		int row=userMapper.updateUser(user);
		if(row!=1) {
			throw new BaseException("修改用户失败");
		}
		String userid=user.getUserid();
		if(StringUtils.isEmpty(rolestr)) {
			userMapper.delUserRole(userid);
		}else {
			String[] roleIdList=rolestr.split(",");
			userMapper.delUserRole(userid);
			for(String roleid:roleIdList) {
				row=userMapper.addUserRole(userid, roleid);
				if(row!=1) {
					throw new BaseException("添加roleid="+roleid+"失败");
				}
			}
		}
		
		return ResultBody.success();
	}
	
	@Transactional()
	public Object addUser(User user,String rolestr) {
		if(user==null) {
			throw new BaseException("参数为空");
		}else if(userMapper.selectUser(user.getUseraccount())!=null){
			throw new BaseException("已存在的账户");
		}else if(StringUtils.isEmpty(user.getUseraccount())) {
			throw new BaseException("账户号为空");
		}else if(StringUtils.isEmpty(user.getPassword())) {
			throw new BaseException("密码为空");
		}else if(StringUtils.isEmpty(user.getUseraccount())) {
			throw new BaseException("账户号为空");
		}else if(StringUtils.isEmpty(user.getUsername())) {
			throw new BaseException("用户名为空");
		}
		String hashAlgorithmName = "MD5";
		Object credentials = user.getPassword();
		Object salt = ByteSource.Util.bytes(user.getUseraccount());;
		int hashIterations = ShiroConfig.HashIterations;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt,hashIterations);
		user.setPassword(String.valueOf(result));
		int row=userMapper.addUser(user);
		if(row!=1) {
			throw new BaseException("添加用户失败");
		}
		
		return ResultBody.success();
	}
	
	
	
	public List<Menu> getUserMenuList(User user){
		
		List<Menu> list=userMapper.selectUserFirstMenu(user.getUserid());
		
		for(Menu first:list) {
			List<Menu> nextlist=userMapper.selectUserNextMenu(user.getUserid(), first.getMenuid());
			first.setSonMenuList(nextlist);
		}
		
		return list;
	}
	
	public List<Role> selectRoleList() {
		// TODO Auto-generated method stub
		List<Role> rolelist=userMapper.selectRoleList();
		return rolelist;
	}

	
	public Object selectRoleList(int page,int limit) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, limit);
		List<Role> rolelist=selectRoleList();
		PageInfo<Role> pageInfo=new PageInfo<Role>();
		JSONObject obj=new JSONObject();
		obj.putAll(ResultBody.success().toMap());
		obj.put("total",pageInfo.getTotal());
		obj.put("data",rolelist);
		return obj;
	}
	
	public List<String> selectFontLogoList() {	
		List<String> list=userMapper.selectFontLogoList();
		return list;
	}
	
	public Object addRole(Role role) {
		if(StringUtils.isEmpty(role.getRolename()))
			throw new BaseException("角色名不能为空");
		if(userMapper.selectRole(role.getRolename())!=null)
			throw new BaseException("角色名已存在");
		Session session=SecurityUtils.getSubject().getSession();
		User user=(User) session.getAttribute("user");
		role.setManager(user.getUseraccount());
		role.setCreatetime(DateUtil.getNowDateTimeStr());
		
		int row = userMapper.addRole(role);
		if(row!=1)
			throw new BaseException("添加角色失败");
		
		return ResultBody.success("添加成功");
	}
	
	public Object editRole(Role role) {
		if(StringUtils.isEmpty(role.getRoleid()))
			throw new BaseException("角色id不能为空");
		if(StringUtils.isEmpty(role.getRolename()))
			throw new BaseException("角色名不能为空");
		if(StringUtil.compare("1", role.getRoleid()))
			throw new BaseException("超级管理员禁止修改");
		int row = userMapper.updateRole(role);
		if(row!=1)
			throw new BaseException("修改角色信息失败");
		
		return ResultBody.success("修改成功");
	}
	
	public Object delRole(String roleid) {
		if(StringUtils.isEmpty(roleid))
			throw new BaseException("角色id为空");
		if(StringUtil.compare("1", roleid))
			throw new BaseException("超级管理员禁止删除");
		int row = userMapper.deleteRole(roleid);
		if(row!=1)
			throw new BaseException("删除角色失败");
		
		return ResultBody.success("删除成功");
	}
	
	public static void main(String[] args) {
		String json="[{\"title\":\" 首页\",\"id\":\"0\",\"field\":\"menu\",\"spread\":false,\"children\":[{\"title\":\" 查询对账结果汇总-console\",\"id\":\"0.1\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 基础信息\",\"id\":\"1\",\"field\":\"menu\",\"spread\":false,\"children\":[{\"title\":\" 机构管理\",\"id\":\"1.1\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查询机构列表-orgInfoList\",\"id\":\"1.1.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 是否启用机构-editOrgInfoStatus\",\"id\":\"1.1.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 新增机构-addOrginfo\",\"id\":\"1.1.3\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 修改机构信息-editOrginfo\",\"id\":\"1.1.4\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 商户管理\",\"id\":\"1.2\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看商户列表-mchList\",\"id\":\"1.2.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 添加商户信息-addMch\",\"id\":\"1.2.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 修改商户信息-editMch\",\"id\":\"1.2.3\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 删除商户-delMch\",\"id\":\"1.2.4\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 业务管理\",\"id\":\"1.3\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看机构业务类型列表-orgBusinessList\",\"id\":\"1.3.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 添加机构业务类型-addOrgBusiness\",\"id\":\"1.3.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 删除机构业务类型-delOrgBusiness\",\"id\":\"1.3.3\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 添加业务类型-addBusiness\",\"id\":\"1.3.4\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 修改业务类型-editBusiness\",\"id\":\"1.3.5\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 删除业务类型-delBusiness\",\"id\":\"1.3.6\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查看业务类型列表-businessList\",\"id\":\"1.3.7\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 服务渠道管理\",\"id\":\"1.4\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看机构渠道列表-channelList\",\"id\":\"1.4.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 添加机构渠道-addChannel\",\"id\":\"1.4.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 修改机构渠道-editChannel\",\"id\":\"1.4.3\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 删除机构渠道-delChannel\",\"id\":\"1.4.4\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 终端管理\",\"id\":\"1.5\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看终端列表-termList\",\"id\":\"1.5.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 新增终端-addTerm\",\"id\":\"1.5.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 修改终端-editTerm\",\"id\":\"1.5.3\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 删除终端-delTerm\",\"id\":\"1.5.4\",\"field\":\"permission\",\"checked\":true}]}]},{\"title\":\" 对账管理\",\"id\":\"2\",\"field\":\"menu\",\"spread\":false,\"children\":[{\"title\":\" 对账汇总\",\"id\":\"2.1\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 手工对账-contrast\",\"id\":\"2.1.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查询对账汇总-totalReconciliation\",\"id\":\"2.1.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查看对账汇总明细-totalReconciliationByPayType\",\"id\":\"2.1.3\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 对账明细\",\"id\":\"2.2\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查询对账明细-totalBills\",\"id\":\"2.2.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查询账目信息-getBillInfo\",\"id\":\"2.2.2\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 差错分析\",\"id\":\"2.3\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看差错账目列表-errorTotalBills\",\"id\":\"2.3.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查看账目信息-getBillInfo\",\"id\":\"2.3.2\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 差错账目审核提交-billApply\",\"id\":\"2.3.3\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 对账记录\",\"id\":\"2.4\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看对账记录-contrastLog\",\"id\":\"2.4.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 查看商户账单下载日志-mchbillsDownloadLog\",\"id\":\"2.4.2\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 机构特殊账单\",\"id\":\"2.5\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看机构特殊账单-totalOrgSpecialBills\",\"id\":\"2.5.1\",\"field\":\"permission\",\"checked\":true},{\"title\":\" 导出机构特殊账单-exportOrgSpecialBills\",\"id\":\"2.5.2\",\"field\":\"permission\",\"checked\":true}]},{\"title\":\" 商户特殊账单\",\"id\":\"2.6\",\"field\":\"menu\",\"checked\":false,\"spread\":true,\"children\":[{\"title\":\" 查看商户特殊账单-totalMchSpecialBills\",\"id\":\"2.6.1\",\"field\":\"permission\",\"checked\":true}]}]}]";
		JSONArray arry=JSON.parseArray(json);
		List<String> menus=new ArrayList<String>();
		List<String> pers=new ArrayList<String>();
		
		for(int i=0;i<arry.size();i++) {
			JSONObject menu=arry.getJSONObject(i);
			menus.add( menu.getString("id"));
			for(int j=0;j< menu.getJSONArray("children").size();j++) {
				JSONObject obj=menu.getJSONArray("children").getJSONObject(j);
				 if(StringUtil.compare("menu", obj.getString("field"))) {
						menus.add( obj.getString("id"));
						Iterator it = obj.getJSONArray("children").iterator();  
						while(it.hasNext()) {
							 JSONObject dt=(JSONObject) it.next();
							 if(StringUtil.compare("menu", dt.getString("field"))) {
									menus.add( dt.getString("id"));
							 }else {
									pers.add(dt.getString("id"));
							 }
							 if(dt.containsKey("children")) {
								 it=dt.getJSONArray("children").iterator(); 
								 continue;
							 }
						}
				 }else {
						pers.add(obj.getString("id"));
				 }
			}

		}
		
		
		System.out.println(menus);
		System.out.println(pers);
	}
	
	@Transactional
	public Object updatePermission(String roleid,JSONArray arry) {
		if(StringUtils.isEmpty(roleid))
			throw new BaseException("角色id为空");
	
		List<String> menus=new ArrayList<String>();
		List<String> pers=new ArrayList<String>();
		if(arry.size()==0) {//移除所有权限
			userMapper.deleteRoleMenu(roleid);
			userMapper.deleteRolePermission(roleid);
			return ResultBody.success();
		}
		
		for(int i=0;i<arry.size();i++) {
			JSONObject menu=arry.getJSONObject(i);
			menus.add( menu.getString("id"));
			for(int j=0;j< menu.getJSONArray("children").size();j++) {
				JSONObject obj=menu.getJSONArray("children").getJSONObject(j);
				 if(StringUtil.compare("menu", obj.getString("field"))) {
						menus.add( obj.getString("id"));
						Iterator it = obj.getJSONArray("children").iterator();  
						while(it.hasNext()) {
							 JSONObject dt=(JSONObject) it.next();
							 if(StringUtil.compare("menu", dt.getString("field"))) {
									menus.add( dt.getString("id"));
							 }else {
									pers.add(dt.getString("id"));
							 }
							 if(dt.containsKey("children")) {
								 it=dt.getJSONArray("children").iterator(); 
								 continue;
							 }
						}
				 }else {
						pers.add(obj.getString("id"));
				 }
			}

		}
		
		if(!menus.isEmpty()) {
			int row = userMapper.deleteRoleMenu(roleid);
			row = userMapper.insertRoleMenu(roleid, menus);
		}
		if(!pers.isEmpty()) {
			int row = userMapper.deleteRolePermission(roleid);
			row = userMapper.insertRolePermission(roleid, pers);
		}
		return ResultBody.success();
		
		
	}
}
