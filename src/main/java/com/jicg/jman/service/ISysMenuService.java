package com.jicg.jman.service;

import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jicg on 2020/4/17
 */
public interface ISysMenuService {
    List<SysMenu> queryMenusByPid(Long pid);

    List<MenuVo> queryAllMenus(SysUser user);

    List<MenuVo> queryAllMenus();

    boolean checkPerm(SysUser user, String perm);

    List<SysMenu> queryChildPermsByMeanId(SysUser user, Long menuid);

    void deleteById(long id);
}
