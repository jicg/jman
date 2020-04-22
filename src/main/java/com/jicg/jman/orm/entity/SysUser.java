package com.jicg.jman.orm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jicg on 2020/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends Base {
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String remark;
}