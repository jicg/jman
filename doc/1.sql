create TABLE `sys_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int(11) DEFAULT '0' COMMENT '菜单排序',
  `perm` varchar(20) NOT NULL DEFAULT '' COMMENT '权限标识',
  `action_type` tinyint(1) unsigned  DEFAULT 1 COMMENT '类型(0:顶级目录,1:菜单,2:操作,)',
  `status` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `creater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id`  int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp  NULL  DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `href` (`href`),
  index `perm` (`perm`),
  index `action_type` (`action_type`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单表';

create TABLE `sys_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',

  `email` varchar(100)    not null COMMENT '邮箱',
  `username` varchar(100) not null COMMENT '用户名',
  `password` varchar(100) not null COMMENT '密码',
  `mobile` varchar(20)    not null COMMENT '手机',
  `remark` varchar(255)   default '' COMMENT '备注信息',

  `creater_id` int(11)  NULL DEFAULT NULL COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updater_id`  int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  unique KEY `email` (`email`),
  unique KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

INSERT INTO `jman`.`sys_user`( `email`, `username`, `password`, `mobile`, `remark`, `creater_id`, `create_at`, `updater_id`, `update_at`) VALUES ( 'root@qq.com', 'root', '$2a$10$itksZ0lFqL/t56CLMf81lOAUGVEV6QT1RXlkPqNtHT3vMC2IjITzS', '18712312312', '系统用户-超级管理员', 0, '2020-04-20 10:18:15', 0, '2020-04-20 10:18:19');

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
  `code` varchar(255) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`,`name`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

SET FOREIGN_KEY_CHECKS = 1;

create TABLE `sys_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',

  `user_id` int  COMMENT '用户id',
  `role_id` int   COMMENT '角色id',
  PRIMARY KEY (`id`),
  index `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户与角色关系表';


create TABLE `sys_role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int  COMMENT '角色id',
  `menu_id` int   COMMENT '菜单id',
  PRIMARY KEY (`id`),
  index `user_id_index` (`role_id`),
	index `menu_id_index` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色与菜单关系表';