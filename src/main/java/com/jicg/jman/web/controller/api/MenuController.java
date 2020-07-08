package com.jicg.jman.web.controller.api;

import com.jicg.jman.bean.vo.R;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.service.impl.SysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/3/9
 */
@ApiOperation("菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("/query")
    @ApiOperation("查询菜单")
    public List<MenuVo> mean() {
        return new ArrayList<>();
    }

    @PostMapping("/new")
    @ApiOperation("新增菜单")
    public String add(MenuVo menuVo) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuVo, menu);
        sysMenuService.save(menu);
        return "ok";
    }


    public String del(@RequestParam("id") int id) {
        sysMenuService.deleteById(id);
        return "ok";
    }

    @GetMapping("/load")
    @ApiOperation("新增菜单")
    public R<List<MenuVo>> load() {
        return R.ok(sysMenuService.queryAllMenus());
    }
}
