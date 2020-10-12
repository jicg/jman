package com.jicg.jman.orm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Configuration;

/**
 * @author jicg on 2020/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@TableName("sys_role")
public class SysRole extends Base {
    private String name;
    private String remark;
}