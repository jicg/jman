package com.jicg.jman.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.orm.mapper.SysUserMapper;
import com.jicg.jman.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * @author jicg on 2020/8/23
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public IPage<UserVo> selectPageVo(Page<?> page) {
        return this.baseMapper.selectPageVo(page);
    }
}
