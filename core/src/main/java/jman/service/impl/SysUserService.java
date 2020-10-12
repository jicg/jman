package com.jicg.jman.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.orm.mapper.SysMenuMapper;
import com.jicg.jman.orm.mapper.SysRoleMapper;
import com.jicg.jman.orm.mapper.SysUserMapper;
import com.jicg.jman.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jicg on 2020/8/23
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final SysRoleMapper roleMapper;

    public SysUserService(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public IPage<UserVo> selectPageVo(Page<?> page) {
        return this.baseMapper.selectPageVo(page);
    }

    @Override
    public List<SysRole> queryRolesByUserId(long userId) {
        return this.baseMapper.queryRolesByUserId(userId);
    }

    @Transactional
    @Override
    public void saveUserRoles(long userId, List<Long> roleIds) {
        List<Long> sysRoleList = roleMapper.queryRolesByUserId(userId);
//        List<SysMenu> has = menus.stream()
//                .filter(menu -> menuIds.contains(menu.getId())
//                ).collect(Collectors.toList());
        List<Long> delIds = sysRoleList.stream()
                .filter(menu -> !roleIds.contains(menu))
                .collect(Collectors.toList());

        List<Long> insertIds = roleIds.stream()
                .filter(menu -> !sysRoleList.contains(menu))
                .collect(Collectors.toList());
        if (insertIds.size() > 0)
            roleMapper.insertMenuIdByRoleId(userId, insertIds);
        if (delIds.size() > 0)
            roleMapper.deleteMenuIdByRoleId(userId, delIds);
    }
}
