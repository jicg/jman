package com.jicg.jman.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysRole;

import java.util.List;

/**
 * @author jicg on 2020/8/14
 */
public interface ISysRoleService extends IService<SysRole> {
    void saveRoleMenus(long roleId, List<Long> menuIds);

    List<SysMenu> queryMenusByRoleId(long roleId);
//    IPage<SysRole> list(Page<SysRole> page);

}