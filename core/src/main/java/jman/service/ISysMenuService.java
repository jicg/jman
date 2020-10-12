package com.jicg.jman.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysUser;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;

/**
 * @author jicg on 2020/4/17
 */
public interface ISysMenuService  extends IService<SysMenu> {
//    List<SysMenu> queryMenusByPid(Long pid);

    List<MenuVo> queryAllMenus(SysUser user);

    void clearCache(SysUser user);

    List<MenuVo> queryAllMenus();


    boolean checkPerm(SysUser user, String perm);

    List<SysMenu> queryChildPermsByMeanId(SysUser user, Long menuid);

    void deleteById(long id);

    List<TreeBeanVo> queryTreeMenus();

    SysMenu queryMenuById(long id);
}
