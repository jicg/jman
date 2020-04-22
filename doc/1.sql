CREATE TABLE `sys_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `href` varchar(100) NOT NULL DEFAULT '' COMMENT '链接',
  `target` varchar(20) NOT NULL DEFAULT '_self' COMMENT '链接打开方式',
  `sort` int(11) DEFAULT '0' COMMENT '菜单排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态(0:禁用,1:启用)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
	`creater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
`updater_id`  int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_at` timestamp  NULL  DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `href` (`href`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统菜单表';

CREATE TABLE `sys_user` (
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