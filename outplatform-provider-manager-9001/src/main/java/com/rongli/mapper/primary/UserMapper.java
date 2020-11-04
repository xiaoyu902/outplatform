package com.rongli.mapper.primary;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongli.config.redis.RedisCache;
import com.rongli.entities.Menu;
import com.rongli.entities.Permission;
import com.rongli.entities.Role;
import com.rongli.entities.User;
import com.rongli.entities.params.ServiceEntity;

@CacheNamespace(implementation= RedisCache.class,eviction=RedisCache.class)
public interface UserMapper extends BaseMapper{
	
	@Select("select * from t_role where rolename=#{rolename}")
	public Role selectRole(String rolename);
	
	@Insert("insert into t_role(rolename,roledesc,createtime,manager)value(#{rolename},#{roledesc},#{createtime},#{manager})")
	public int addRole(Role role);
	
	@Delete("delete from t_role where roleid=#{roleid}")
	public int deleteRole(String roleid);
	
	public int updateRole(Role role);

	@Select("select * from t_user where useraccount=#{useraccount}")
	@Results(id="UserMap",value={
			@Result(id=true, column="userid", property="userid",jdbcType=JdbcType.INTEGER),
			@Result(column="useraccount", property="useraccount", jdbcType=JdbcType.VARCHAR),
			@Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
			@Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
			@Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
			@Result(column="mail", property="mail", jdbcType=JdbcType.VARCHAR),
			@Result(column="isactive", property="isactive", jdbcType=JdbcType.VARCHAR),
			@Result(column="userid",property="roleList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectRoleListByUser")),
			})
	public User selectUser(String useraccount);
	
	
	@Select("<script>"
			+ "select * from t_user where 1=1 "
			+ "<if test='useraccount!=null and useraccount != \"\"'>and useraccount=#{useraccount} </if>"
			+ "</script>")
	@ResultMap(value = "UserMap")
	public List<User> selectUserList(@Param("useraccount")String useraccount);
	
	
	@Select("SELECT\r\n" + 
			"	a.*,\r\n" + 
			"	b.userid \r\n" + 
			"FROM\r\n" + 
			"	t_role a\r\n" + 
			"	LEFT JOIN t_user_role b ON a.roleid = b.roleid \r\n" + 
			"WHERE\r\n" + 
			"	b.userid = #{userid}")
	@Results({
		@Result(id=true, column="roleid", property="roleid",jdbcType=JdbcType.INTEGER),
		@Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
		@Result(column="userid", property="userid", jdbcType=JdbcType.VARCHAR),
		@Result(column="roledesc", property="roledesc", jdbcType=JdbcType.VARCHAR),
		@Result(column="createtime", property="createtime", jdbcType=JdbcType.VARCHAR),
		@Result(column="manager", property="manager", jdbcType=JdbcType.VARCHAR),
		@Result(column="roleid",property="menuList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectFirstMenuList")),
		})
	public List<Role> selectRoleListByUser(String userid);
	
	
	/**
	 * @return 返回角色集合
	 */
	@Select("select * from t_role")
	@Results({
		@Result(id=true, column="roleid", property="roleid",jdbcType=JdbcType.INTEGER),
		@Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
		@Result(column="roledesc", property="roledesc", jdbcType=JdbcType.VARCHAR),
		@Result(column="createtime", property="createtime", jdbcType=JdbcType.VARCHAR),
		@Result(column="manager", property="manager", jdbcType=JdbcType.VARCHAR),
		@Result(column="roleid",property="menuList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectFirstMenuList")),
		})
	public List<Role> selectRoleList();
	
	@Select("SELECT\r\n" + 
			"	a.*,b.roleid \r\n" + 
			"FROM\r\n" + 
			"	t_menu a\r\n" + 
			"	LEFT JOIN t_role_menu b ON a.menuid = b.menuid \r\n" + 
			"WHERE\r\n" + 
			"	b.roleid = #{roleid} \r\n" + 
			"	AND (ISNULL( a.fmenuid ) or a.fmenuid='')\r\n" + 
			"ORDER BY\r\n" + 
			"	a.sort ASC")
	@Results({
		@Result(id=true, column="menuid", property="menuid",jdbcType=JdbcType.VARCHAR),
		@Result(column="fontlogo", property="fontlogo", jdbcType=JdbcType.VARCHAR),
		@Result(column="roleid", property="roleid", jdbcType=JdbcType.VARCHAR),
		@Result(column="menuname", property="menuname", jdbcType=JdbcType.VARCHAR),
		@Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
		@Result(column="isinside", property="isinside", jdbcType=JdbcType.VARCHAR),
		@Result(column="linkurl", property="linkurl", jdbcType=JdbcType.VARCHAR),
		@Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
		@Result(column="{menuid=menuid,roleid=roleid}",property="sonMenuList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectNextMenuList")),
		@Result(column="{menuid=menuid,roleid=roleid}",property="permissionList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectPermissionList")),
		})
	public List<Menu> selectFirstMenuList(String roleid);
	
	@Select("SELECT\r\n" + 
			"	c.*,b.roleid \r\n" + 
			"FROM\r\n" + 
			"	t_role a\r\n" + 
			"	LEFT JOIN t_role_menu b ON a.roleid = b.roleid\r\n" + 
			"	LEFT JOIN t_menu c ON c.menuid = b.menuid where c.fmenuid=#{menuid}  and b.roleid=#{roleid} order by c.sort desc")
	@Results({
		@Result(id=true, column="menuid", property="menuid",jdbcType=JdbcType.VARCHAR),
		@Result(column="fmenuid", property="fmenuid", jdbcType=JdbcType.VARCHAR),
		@Result(column="fontlogo", property="fontlogo", jdbcType=JdbcType.VARCHAR),
		@Result(column="roleid", property="roleid", jdbcType=JdbcType.VARCHAR),
		@Result(column="menuname", property="menuname", jdbcType=JdbcType.VARCHAR),
		@Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
		@Result(column="isinside", property="isinside", jdbcType=JdbcType.VARCHAR),
		@Result(column="linkurl", property="linkurl", jdbcType=JdbcType.VARCHAR),
		@Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
		@Result(column="{menuid=menuid,roleid=roleid}",property="permissionList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectPermissionList")),
		@Result(column="{menuid=menuid,roleid=roleid}",property="sonMenuList",many=@Many(select="com.rongli.mapper.primary.UserMapper.selectNextMenuList"))
	})
	public List<Menu> selectNextMenuList(String menuid,String roleid);
	
	@Select("select a.* FROM t_permission a LEFT JOIN t_role_permission b ON a.perid=b.perid where b.roleid=#{roleid} and a.menuid=#{menuid}")
	public List<Permission> selectPermissionList(String roleid,String menuid);
	
	
	@Select("select DISTINCT c.* from t_role a  JOIN t_role_menu b on a.roleid=b.roleid  JOIN t_menu c ON c.menuid=b.menuid where a.roleid in (SELECT c.roleid FROM t_user a	LEFT JOIN t_user_role b ON a.userid = b.userid	LEFT JOIN t_role c ON b.roleid =c.roleid where a.userid=#{userid}) and (isnull(c.fmenuid) or c.fmenuid='') order by c.sort asc")
	public List<Menu> selectUserFirstMenu(String userid);
	
	@Select(" select DISTINCT c.* from t_role a  JOIN t_role_menu b on a.roleid=b.roleid  JOIN t_menu c ON c.menuid=b.menuid where a.roleid in (SELECT c.roleid FROM t_user a	LEFT JOIN t_user_role b ON a.userid = b.userid	LEFT JOIN t_role c ON b.roleid =c.roleid where a.userid=#{userid}) and c.fmenuid=#{menuid} order by c.sort asc")
	public List<Menu> selectUserNextMenu(String userid,String menuid);
	
	public int addUser(User user);
	
	@Insert("insert into t_user_role(userid,roleid) values(#{userid},#{roleid})")
	public int addUserRole(String userid,String roleid);

	@Insert("delete from t_user_role where userid=#{userid}")
	public int delUserRole(String userid);
	
	@Insert("delete from t_user where userid=#{userid}")
	public int deleteUser(String userid);
	
	public int updateUser(User user);

	
	@Select("select fontlogo from t_dicfontlogo")
	public List<String> selectFontLogoList();

	
	@Delete("delete from t_role_permission where roleid=#{roleid}")
	public int deleteRolePermission(String roleid);
	
	@Delete("delete from t_role_menu where roleid=#{roleid}")
	public int deleteRoleMenu(String roleid);
	
	public int insertRoleMenu(String roleid,List<String> menus);
	
	public int insertRolePermission(String roleid,List<String> pers);

}
