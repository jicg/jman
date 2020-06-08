package com.jicg.jman.web.controller.api;

import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.mapper.SysMenuMapper;
import com.jicg.jman.bean.vo.MenuVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/3/9
 */
@ApiOperation("菜单接口")
@RestController
public class MenuController {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @GetMapping("/mean")
    @ApiOperation("查询菜单")
    public List<MenuVo> mean() {
        return new ArrayList<>();
    }

    @PostMapping("/mean/new")
    @ApiOperation("新增菜单")
    public String add(MenuVo menuVo) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuVo, menu);
        sysMenuMapper.insert(menu);
        return "ok";
    }
}
