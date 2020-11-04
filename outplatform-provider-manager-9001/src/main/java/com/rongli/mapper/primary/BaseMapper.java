package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import com.rongli.config.redis.RedisCache;
import com.rongli.entities.Menu;
import com.rongli.entities.Permission;
import com.rongli.entities.Term;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface BaseMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper{
	
	
	/**根据支付类型查询终端号
	 * @param orgno
	 * @param mchno
	 * @return
	 */
	public List<Term> selectTermList(String orgno,String termid,List<String> mchs,List<String> channels);
	
	/**添加终端信息
	 * @param list
	 * @return
	 */
	public int addTermList(List<Term> list);
	
	/**更新终端状态
	 * @param channelno
	 * @param termname
	 * @param address
	 * @param remark
	 * @param orgno
	 * @param termid
	 * @return
	 */
	public int updateTerm(String channelno,String termname,String address,String remark,String orgno,String termid,String mchno);
	
	/**删除终端
	 * @param orgno
	 * @param termid
	 * @return
	 */
	public int delTerm(String orgno,String termid);
	

	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<Menu> selectMenuList();

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu);

	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	public int addMenu(Menu menu);

	/**
	 * 删除菜单
	 * @param menuid
	 * @return
	 */
	public int delMenuByMenuId(String menuid);
	
	
	/**查看系统菜单
	 * @return
	 */
	public List<Menu> selectAllMenuList();
	
	
	/**查看二级菜单
	 * @param menuid
	 * @return
	 */
	public List<Menu> selectSonMenuList(String menuid);
	
	/**查询菜单下所有权限
	 * @param menuid
	 * @return
	 */
	public List<Permission> selectPermissionList(String menuid);
	
	/**添加权限
	 * @param permission
	 * @return
	 */
	public int addPermission(Permission permission);
	
	/**删除权限
	 * @param perid
	 * @return
	 */
	public int deletePermission(String perid);
	
	/**更新权限
	 * @param permission
	 * @return
	 */
	public int updatePermission(Permission permission);
	
	/**
	 * @param perid
	 * @param permission
	 * @return
	 */
	@Select("select * from t_permission where perid=#{perid} or permission=#{permission}")
	public Permission selectPermission(String perid,String permission);
}
