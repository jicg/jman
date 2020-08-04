package com.jicg.jman.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.orm.mapper.SysMenuMapper;
import com.jicg.jman.service.ISysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jicg on 2020/4/17
 */
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu>
        implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryMenusByPid(Long pid) {
        return sysMenuMapper.lambdaQueryChain()
                .eq(SysMenu::getActionType, 1)
                .eq(SysMenu::getPid, pid)
                .eq(SysMenu::getStatus, 1)
                .orderByAsc(SysMenu::getSort).list();
    }

    @Override
    public List<MenuVo> queryAllMenus(SysUser user) {
        List<SysMenu> sysMenus = new ArrayList<>();
        if ("root".equals(user.getUsername())) {
            sysMenus = sysMenuMapper.lambdaQueryChain()
                    .eq(SysMenu::getActionType, 1)
                    .orderByAsc(SysMenu::getSort).list();
        } else {
            sysMenus = sysMenuMapper.queryMenusByUserId(user.getId());
        }
        return getMenuVos(sysMenus);
    }


    @Override
    public List<MenuVo> queryAllMenus() {
        List<SysMenu> sysMenus = sysMenuMapper.lambdaQueryChain()
                .eq(SysMenu::getActionType, 1)
                .orderByAsc(SysMenu::getSort).list();
        List<MenuVo> rets = new ArrayList<>();
        sysMenus.forEach(sysMenu -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(sysMenu, menuVo);
            rets.add(menuVo);
        });
        return rets;
    }


    @Override
    public List<TreeBeanVo> queryTreeMenus() {
        List<SysMenu> sysMenus = sysMenuMapper.lambdaQueryChain()
                .eq(SysMenu::getActionType, 1)
                .orderByAsc(SysMenu::getSort).list();
        return getToTreeVos(sysMenus);
    }

    @Override
    public SysMenu queryMenuById(long id) {
        SysMenu sysMenu = sysMenuMapper.findOne(id);
        return sysMenu;
    }


    private List<TreeBeanVo> getToTreeVos(List<SysMenu> sysMenus) {
        Map<Long, TreeBeanVo> maps = new LinkedHashMap<>();
        sysMenus.forEach(sysMenu -> {
            if (sysMenu.getStatus() == 1) {
                TreeBeanVo treeVo = new TreeBeanVo();
                treeVo.setId(sysMenu.getId());
                treeVo.setPid(sysMenu.getPid());
                treeVo.setName(sysMenu.getTitle());
                treeVo.setOpen(true);
                maps.put(sysMenu.getId(), treeVo);
            }
        });
        maps.forEach((k, v) -> {
            if (v.getPid() > 0) {
                if (maps.containsKey(v.getPid())) {
                    maps.get(v.getPid()).getChildren().add(v);
                }
            }
        });
        maps.forEach((k, v) -> {
            if (v.getChildren() != null && v.getChildren().size() <= 0) {
                v.setChildren(null);
            }
        });
        return maps.values().stream()
                .filter(x -> x.getPid() <= 0)
                .collect(Collectors.toList());
    }

    private List<MenuVo> getMenuVos(List<SysMenu> sysMenus) {
        Map<Long, MenuVo> maps = new LinkedHashMap<>();
        sysMenus.forEach(sysMenu -> {
            if (sysMenu.getStatus() == 1) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(sysMenu, menuVo);
                maps.put(sysMenu.getId(), menuVo);
            }
        });
        maps.forEach((k, v) -> {
            if (v.getPid() > 0) {
                if (maps.containsKey(v.getPid())) {
                    maps.get(v.getPid()).getChild().add(v);
                }
            }
        });
        return maps.values().stream()
                .filter(x -> x.getPid() <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkPerm(SysUser user, String perm) {
        return sysMenuMapper.queryCntPerm(user.getId(), perm) > 0;
    }

    @Override
    public List<SysMenu> queryChildPermsByMeanId(SysUser user, Long menuid) {
        return sysMenuMapper.queryChildPermsByMeanId(user.getId(), menuid);
    }

    @Override
    public void deleteById(long id) {
        sysMenuMapper.deleteById(id);
    }

}
