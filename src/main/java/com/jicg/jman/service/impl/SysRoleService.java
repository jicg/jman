package com.jicg.jman.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.mapper.SysRoleMapper;
import com.jicg.jman.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author jicg on 2020/8/14
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole>
        implements ISysRoleService {
}
