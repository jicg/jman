package com.jicg.jman.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.orm.mapper.SysUserMapper;

import java.util.List;

/**
 * @author jicg on 2020/8/23
 */

public interface ISysUserService extends IService<SysUser> {
    IPage<UserVo> selectPageVo(Page<?> page);

    List<SysRole> queryRolesByUserId(long userId);
}
