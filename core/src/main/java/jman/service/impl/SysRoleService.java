package com.jicg.jman.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.orm.entity.Base;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.mapper.SysMenuMapper;
import com.jicg.jman.orm.mapper.SysRoleMapper;
import com.jicg.jman.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jicg on 2020/8/14
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole>
        implements ISysRoleService {

    private final SysMenuMapper menuMapper;

    public SysRoleService(SysMenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Transactional
    @Override
    public void saveRoleMenus(long roleId, List<Long> menuIds) {
        List<Long> sysMenuIds = menuMapper.queryMenuIdsByRoleId(roleId);
//        List<SysMenu> has = menus.stream()
//                .filter(menu -> menuIds.contains(menu.getId())
//                ).collect(Collectors.toList());
        List<Long> delIds = sysMenuIds.stream()
                .filter(menu -> !menuIds.contains(menu))
                .collect(Collectors.toList());

        List<Long> insertIds = menuIds.stream()
                .filter(menu -> !sysMenuIds.contains(menu))
                .collect(Collectors.toList());
        if (insertIds.size() > 0)
            menuMapper.insertMenuIdByRoleId(roleId, insertIds);
        if (delIds.size() > 0)
            menuMapper.deleteMenuIdByRoleId(roleId, delIds);
    }

    @Override
    public List<SysMenu> queryMenusByRoleId(long roleId) {
        return menuMapper.queryMenusByRoleId(roleId);
    }
}
