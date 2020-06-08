package com.jicg.jman.orm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jicg on 2020/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends Base {
    private String title;
    private String href;
    private Long pid = 0L;
    private String icon;
    private String target;
    //权限标识
    private String perm;
    //菜单类型 1:菜单，2:其他操作
    private int actionType = 1;
    private Integer sort = 1;
    //状态(0:禁用,1:启用
    private int status = 1;
    private String remark;
}