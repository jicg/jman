/*
 Navicat MySQL Data Transfer

 Source Server         : mysql-nas
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.2.88:3306
 Source Schema         : jman

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 11/09/2020 09:05:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int DEFAULT '0' COMMENT '菜单排序',
  `perm` varchar(20) NOT NULL DEFAULT '' COMMENT '权限标识',
  `action_type` tinyint unsigned DEFAULT '1' COMMENT '类型(0:顶级目录,1:菜单,2:操作,)',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `creater_id` int unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` int DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `href` (`href`),
  KEY `perm` (`perm`),
  KEY `action_type` (`action_type`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (250, 0, '系统设置', '', '', '_self', 100, 'sys', 0, 1, NULL, 0, NULL, 250, '2020-08-05 21:28:43');
INSERT INTO `sys_menu` VALUES (251, 250, '菜单管理', 'layui-icon layui-icon layui-icon-template-1', 'menu/index.html', '_self', 10, 'sys:menu:index', 1, 1, NULL, 1, '2020-08-01 10:58:33', 250, '2020-08-06 07:45:48');
INSERT INTO `sys_menu` VALUES (263, 276, '用户管理', 'layui-icon layui-icon layui-icon layui-icon layui-icon layui-icon-username', 'user/index.html', '_self', 20, 'sys:user:index', 1, 1, NULL, 250, '2020-08-05 18:48:03', 250, '2020-08-09 22:01:48');
INSERT INTO `sys_menu` VALUES (265, 251, '菜单新增', 'layui-icon layui-icon layui-icon-add-1', 'menu/add.html', '_self', 10, 'sys:menu:add', 2, 1, NULL, 250, '2020-08-06 07:47:31', 250, '2020-08-06 07:48:54');
INSERT INTO `sys_menu` VALUES (266, 251, '菜单修改', 'layui-icon layui-icon layui-icon layui-icon-edit', 'menu/add.html', '_self', 20, 'sys:menu:edit', 2, 1, NULL, 250, '2020-08-06 07:48:14', 250, '2020-08-06 07:49:33');
INSERT INTO `sys_menu` VALUES (267, 250, '设置', 'layui-icon layui-icon layui-icon-set', 'set/index.html', '_self', 40, 'sys:set:index', 1, 1, NULL, 250, '2020-08-06 07:53:10', 250, '2020-08-06 11:29:41');
INSERT INTO `sys_menu` VALUES (268, 0, '表单配置', 'layui-icon layui-icon-website', '', '_self', 1000, 'form_builder', 0, 1, NULL, 250, '2020-08-06 10:04:39', 250, '2020-08-06 10:04:39');
INSERT INTO `sys_menu` VALUES (269, 268, '表定义', 'layui-icon layui-icon layui-icon layui-icon layui-icon-table', 'form_builder/table.html', '_self', 10, 'form_builder:table', 1, 1, NULL, 250, '2020-08-06 10:21:23', 250, '2020-08-06 10:26:28');
INSERT INTO `sys_menu` VALUES (270, 268, '字段', 'layui-icon layui-icon layui-icon-tabs', 'form_builder/col.html', '_self', 20, 'form_builder:col', 1, 1, NULL, 250, '2020-08-06 10:23:04', 250, '2020-08-06 10:23:15');
INSERT INTO `sys_menu` VALUES (271, 250, '任务管理', 'layui-icon layui-icon layui-icon layui-icon layui-icon-engine', 'job/index.html', '_self', 30, 'sys:job:index', 1, 1, NULL, 250, '2020-08-06 11:29:11', 250, '2020-08-06 11:30:07');
INSERT INTO `sys_menu` VALUES (272, 276, '角色管理', 'layui-icon layui-icon layui-icon layui-icon layui-icon-user', 'role/index.html', '_self', 20, 'role:index', 1, 1, NULL, 250, '2020-08-09 21:31:41', 250, '2020-08-12 21:50:33');
INSERT INTO `sys_menu` VALUES (276, 250, '权限管理', 'layui-icon layui-icon layui-icon-group', '', '_self', 20, '', 0, 1, NULL, 250, '2020-08-09 21:35:42', 250, '2020-08-09 22:02:51');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '角色名',
  `remark` varchar(2000) DEFAULT '' COMMENT '备注信息',
  `creater_id` int DEFAULT NULL COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` int DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`,`name`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (250, '超级管理员', '超级管理员', 250, '2020-08-24 18:47:57', 250, '2020-09-05 17:32:34', NULL);
INSERT INTO `sys_role` VALUES (251, '系统管理员', '系统管理员', 250, '2020-09-05 17:31:59', 250, '2020-09-05 17:31:59', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  `menu_id` int DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`role_id`),
  KEY `menu_id_index` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色与菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (250, 250, 277);
INSERT INTO `sys_role_menu` VALUES (251, 250, 250);
INSERT INTO `sys_role_menu` VALUES (252, 250, 267);
INSERT INTO `sys_role_menu` VALUES (253, 250, 271);
INSERT INTO `sys_role_menu` VALUES (254, 250, 251);
INSERT INTO `sys_role_menu` VALUES (255, 250, 265);
INSERT INTO `sys_role_menu` VALUES (256, 250, 266);
INSERT INTO `sys_role_menu` VALUES (264, 251, 277);
INSERT INTO `sys_role_menu` VALUES (265, 250, 276);
INSERT INTO `sys_role_menu` VALUES (266, 250, 263);
INSERT INTO `sys_role_menu` VALUES (267, 250, 272);
INSERT INTO `sys_role_menu` VALUES (268, 250, 268);
INSERT INTO `sys_role_menu` VALUES (269, 250, 269);
INSERT INTO `sys_role_menu` VALUES (270, 250, 270);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `mobile` varchar(20) NOT NULL COMMENT '手机',
  `remark` varchar(255) DEFAULT '' COMMENT '备注信息',
  `creater_id` int DEFAULT NULL COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id` int DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (250, 'root@qq.com', 'root', '$2a$10$itksZ0lFqL/t56CLMf81lOAUGVEV6QT1RXlkPqNtHT3vMC2IjITzS', '123', '123', 0, '2020-04-20 10:18:15', 0, '2020-04-20 10:18:19');
INSERT INTO `sys_user` VALUES (251, '111111@qq.com', 'jicg', '$2a$10$RT2CTipQuyeftxcM8.bBR.2vOrlGDxS.qgVE/0n3hDqERXAErWCmC', '18733332222', '测啊hi的沙发上看到发烧快点放假啊是的', 250, '2020-08-24 10:07:45', 250, '2020-08-24 10:13:04');
INSERT INTO `sys_user` VALUES (261, 'root@aa.com', 'root1', '$2a$10$oL1gB1ZLgDqBXCjKr/brnOJ6MWZ01chABHKfHQcZIKAt2Zfccc85W', '18717792222', 'asdfasdfasd', 250, '2020-08-24 10:51:47', 250, '2020-08-24 10:51:47');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户与角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (250, 250, 250);
INSERT INTO `sys_user_role` VALUES (254, 251, 250);
INSERT INTO `sys_user_role` VALUES (255, 251, 251);
INSERT INTO `sys_user_role` VALUES (257, 250, 251);
INSERT INTO `sys_user_role` VALUES (258, 261, 251);
INSERT INTO `sys_user_role` VALUES (259, 262, 251);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
