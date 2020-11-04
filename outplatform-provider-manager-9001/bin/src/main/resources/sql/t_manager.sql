/*
Navicat MySQL Data Transfer

Source Server         : yuanjian
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : db_outplatform

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2020-10-21 17:31:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_dicfontlogo`
-- ----------------------------
DROP TABLE IF EXISTS `t_dicfontlogo`;
CREATE TABLE `t_dicfontlogo` (
  `fontlogo` varchar(100) NOT NULL default '' COMMENT '字体图标代码',
  `logodesc` varchar(100) NOT NULL default '' COMMENT '字体图标说明',
  PRIMARY KEY  USING BTREE (`fontlogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_dicfontlogo
-- ----------------------------
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-addition', '加');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-app', '应用');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-auz', '授权');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-cols', '列');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-console', '控制台');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-diamond', '钻石-等级');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-email', '邮箱');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-face-smile', '微笑');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-form', '表单');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-gift', '礼物/活动');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-key', '密钥/钥匙');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-light', '亮度/晴');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-notice', '消息-通知');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-rate-half', '半星');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-read', '办公-阅读');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-refresh-3', '刷新');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-service', '客服');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-set', '设置-空心');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-slider', '滑块');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-snowflake', '雪花');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-spread-left', '左向右伸缩菜单');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-subtraction', '减');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-survey', '调查');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-template', '模板');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-theme', '主题');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-time', '时间/历史');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-username', '用户名');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon layui-icon-website', '网站');
INSERT INTO `t_dicfontlogo` VALUES ('layui-icon-chart-screen', '报表');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menuid` varchar(10) NOT NULL COMMENT '菜单id',
  `fmenuid` varchar(10) default NULL COMMENT '父菜单id',
  `fontlogo` varchar(100) default '' COMMENT '图标',
  `menuname` varchar(100) NOT NULL,
  `path` varchar(255) default NULL COMMENT '组件路径',
  `isinside` int(1) NOT NULL default '1' COMMENT '是否内部跳转菜单 0否1是',
  `linkurl` varchar(255) default NULL COMMENT '跳转菜单路径',
  `sort` int(10) default '0' COMMENT '排序',
  PRIMARY KEY  (`menuid`),
  KEY `fontlogo` (`fontlogo`),
  CONSTRAINT `t_menu_ibfk_1` FOREIGN KEY (`fontlogo`) REFERENCES `t_dicfontlogo` (`fontlogo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('0', '', 'layui-icon layui-icon-auz', '首页', null, '1', '/url/home/console', '0');
INSERT INTO `t_menu` VALUES ('3', null, 'layui-icon layui-icon-cols', '系统管理', null, '1', '', '3');
INSERT INTO `t_menu` VALUES ('3.1', '3', 'layui-icon layui-icon-auz', '用户管理', null, '1', '/url/sys/user/index', '1');
INSERT INTO `t_menu` VALUES ('3.2', '3', 'layui-icon layui-icon-auz', '角色管理', null, '1', '/url/sys/role/index', '2');
INSERT INTO `t_menu` VALUES ('3.3', '3', 'layui-icon layui-icon-console', '菜单管理', null, '1', '/url/sys/menu/index', '3');
INSERT INTO `t_menu` VALUES ('3.4', '3', 'layui-icon layui-icon-face-smile', '权限管理', null, '1', '/url/sys/permission/index', '4');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `perid` varchar(10) NOT NULL default '' COMMENT '权限id',
  `permissiondesc` varchar(50) NOT NULL default '' COMMENT '权限描述',
  `permission` varchar(50) default NULL COMMENT '权限传',
  `menuid` varchar(10) NOT NULL COMMENT '按钮id',
  PRIMARY KEY  USING BTREE (`perid`,`menuid`),
  KEY `menuid` (`menuid`),
  KEY `funcid` (`perid`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`menuid`) REFERENCES `t_menu` (`menuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('0.1', '查询对账结果汇总', 'console', '0');
INSERT INTO `t_permission` VALUES ('2.7.1', 'rlpay实时交易查询', 'getPayLogList', '2.7');
INSERT INTO `t_permission` VALUES ('2.7.2', 'rlpay实时交易退款', 'rlPayRefund', '2.7');
INSERT INTO `t_permission` VALUES ('3.1.1', '查询用户列表', 'selectUserList', '3.1');
INSERT INTO `t_permission` VALUES ('3.1.2', '新增用户', 'addUser', '3.1');
INSERT INTO `t_permission` VALUES ('3.1.3', '删除用户', 'delUser', '3.1');
INSERT INTO `t_permission` VALUES ('3.1.4', '修改用户', 'editUser', '3.1');
INSERT INTO `t_permission` VALUES ('3.1.5', '修改用户密码', 'editUserPsw', '3.1');
INSERT INTO `t_permission` VALUES ('3.2.1', '查询角色列表', 'selectRoleList', '3.2');
INSERT INTO `t_permission` VALUES ('3.2.2', '权限管理', 'accessManagement', '3.2');
INSERT INTO `t_permission` VALUES ('3.2.3', '删除角色', 'delRole', '3.2');
INSERT INTO `t_permission` VALUES ('3.2.4', '添加角色', 'addRole', '3.2');
INSERT INTO `t_permission` VALUES ('3.2.5', '修改角色', 'editRole', '3.2');
INSERT INTO `t_permission` VALUES ('3.3.1', '查询菜单', 'selectMenuList', '3.3');
INSERT INTO `t_permission` VALUES ('3.3.2', '修改菜单', 'editMenu', '3.3');
INSERT INTO `t_permission` VALUES ('3.3.3', '添加菜单', 'addMenu', '3.3');
INSERT INTO `t_permission` VALUES ('3.3.4', '删除菜单', 'delMenuByMenuId', '3.3');
INSERT INTO `t_permission` VALUES ('3.4.1', '查看菜单权限列表', 'permissionList', '3.4');
INSERT INTO `t_permission` VALUES ('3.4.2', '新增菜单权限', 'addPermission', '3.4');
INSERT INTO `t_permission` VALUES ('3.4.3', '删除菜单权限', 'delPermission', '3.4');
INSERT INTO `t_permission` VALUES ('3.4.4', '修改菜单权限', 'editPermission', '3.4');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleid` int(10) NOT NULL auto_increment COMMENT '角色id',
  `rolename` varchar(50) NOT NULL COMMENT '角色名',
  `roledesc` varchar(255) default NULL COMMENT '角色描述',
  `createtime` datetime NOT NULL COMMENT '添加时间',
  `manager` varchar(100) NOT NULL COMMENT '添加用户',
  PRIMARY KEY  (`roleid`),
  UNIQUE KEY `rolename` USING BTREE (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '开发者超级管理员', '2020-06-23 15:37:42', 'admin');
INSERT INTO `t_role` VALUES ('2', '机构管理员', '机构管理员', '2020-06-23 15:37:44', 'admn');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `roleid` int(10) NOT NULL COMMENT '角色id',
  `menuid` varchar(10) NOT NULL default '' COMMENT '菜单id',
  PRIMARY KEY  USING BTREE (`roleid`,`menuid`),
  KEY `menuid` (`menuid`),
  CONSTRAINT `t_role_menu_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_role_menu_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `t_menu` (`menuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '0');
INSERT INTO `t_role_menu` VALUES ('2', '0');
INSERT INTO `t_role_menu` VALUES ('1', '3');
INSERT INTO `t_role_menu` VALUES ('2', '3');
INSERT INTO `t_role_menu` VALUES ('1', '3.1');
INSERT INTO `t_role_menu` VALUES ('2', '3.1');
INSERT INTO `t_role_menu` VALUES ('1', '3.2');
INSERT INTO `t_role_menu` VALUES ('2', '3.2');
INSERT INTO `t_role_menu` VALUES ('1', '3.3');
INSERT INTO `t_role_menu` VALUES ('2', '3.3');
INSERT INTO `t_role_menu` VALUES ('1', '3.4');
INSERT INTO `t_role_menu` VALUES ('2', '3.4');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `perid` varchar(10) NOT NULL default '' COMMENT '功能名称',
  `roleid` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY  USING BTREE (`perid`,`roleid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`perid`) REFERENCES `t_permission` (`perid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('0.1', '1');
INSERT INTO `t_role_permission` VALUES ('3.1.1', '1');
INSERT INTO `t_role_permission` VALUES ('3.1.2', '1');
INSERT INTO `t_role_permission` VALUES ('3.1.3', '1');
INSERT INTO `t_role_permission` VALUES ('3.1.4', '1');
INSERT INTO `t_role_permission` VALUES ('3.1.5', '1');
INSERT INTO `t_role_permission` VALUES ('3.2.1', '1');
INSERT INTO `t_role_permission` VALUES ('3.2.2', '1');
INSERT INTO `t_role_permission` VALUES ('3.2.3', '1');
INSERT INTO `t_role_permission` VALUES ('3.2.4', '1');
INSERT INTO `t_role_permission` VALUES ('3.2.5', '1');
INSERT INTO `t_role_permission` VALUES ('3.3.1', '1');
INSERT INTO `t_role_permission` VALUES ('3.3.2', '1');
INSERT INTO `t_role_permission` VALUES ('3.3.3', '1');
INSERT INTO `t_role_permission` VALUES ('3.3.4', '1');
INSERT INTO `t_role_permission` VALUES ('3.4.1', '1');
INSERT INTO `t_role_permission` VALUES ('3.4.2', '1');
INSERT INTO `t_role_permission` VALUES ('3.4.3', '1');
INSERT INTO `t_role_permission` VALUES ('3.4.4', '1');
INSERT INTO `t_role_permission` VALUES ('0.1', '2');
INSERT INTO `t_role_permission` VALUES ('3.1.1', '2');
INSERT INTO `t_role_permission` VALUES ('3.1.2', '2');
INSERT INTO `t_role_permission` VALUES ('3.1.3', '2');
INSERT INTO `t_role_permission` VALUES ('3.1.4', '2');
INSERT INTO `t_role_permission` VALUES ('3.1.5', '2');
INSERT INTO `t_role_permission` VALUES ('3.2.1', '2');
INSERT INTO `t_role_permission` VALUES ('3.2.2', '2');
INSERT INTO `t_role_permission` VALUES ('3.2.3', '2');
INSERT INTO `t_role_permission` VALUES ('3.2.4', '2');
INSERT INTO `t_role_permission` VALUES ('3.2.5', '2');
INSERT INTO `t_role_permission` VALUES ('3.3.1', '2');
INSERT INTO `t_role_permission` VALUES ('3.3.2', '2');
INSERT INTO `t_role_permission` VALUES ('3.3.3', '2');
INSERT INTO `t_role_permission` VALUES ('3.3.4', '2');
INSERT INTO `t_role_permission` VALUES ('3.4.1', '2');
INSERT INTO `t_role_permission` VALUES ('3.4.2', '2');
INSERT INTO `t_role_permission` VALUES ('3.4.3', '2');
INSERT INTO `t_role_permission` VALUES ('3.4.4', '2');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` int(10) NOT NULL auto_increment COMMENT '用户id',
  `useraccount` varchar(20) NOT NULL COMMENT '用户账号',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `phone` varchar(20) default NULL COMMENT '联系电话',
  `mail` varchar(50) default NULL COMMENT '邮箱',
  `isactive` int(1) NOT NULL default '1' COMMENT '1启用 0不启用',
  PRIMARY KEY  (`userid`),
  UNIQUE KEY `useraccount` USING BTREE (`useraccount`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '60d1225991bd2d1428b8916de60222fa', '袁建', '17673116580', '1204432129@qq.com', '1');
INSERT INTO `t_user` VALUES ('2', 'test', '62dfe891bab6f440be1d965218aa714b', '测试', null, null, '1');
INSERT INTO `t_user` VALUES ('3', 'admin1', '852bd12ed63353ab65ecac996b00b0bb', 'admin1', 'admin1', 'admin1', '1');
INSERT INTO `t_user` VALUES ('4', 'test1', 'bc5d6decf30d8d8b78f49144d56756cb', '测试', '123123', '123123', '0');
INSERT INTO `t_user` VALUES ('7', 'test2', '93fa2513426521d680b9012748d1813d', '123', '17673116580', '', '1');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userid` int(10) NOT NULL COMMENT '用户ID',
  `roleid` int(10) NOT NULL COMMENT '角色ID',
  PRIMARY KEY  (`userid`,`roleid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`roleid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('2', '1');
INSERT INTO `t_user_role` VALUES ('3', '1');
INSERT INTO `t_user_role` VALUES ('4', '1');
INSERT INTO `t_user_role` VALUES ('7', '1');
