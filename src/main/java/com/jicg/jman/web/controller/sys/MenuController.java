package com.jicg.jman.web.controller.sys;

import com.jicg.jman.bean.vo.R;
import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.service.impl.SysMenuService;
import com.jicg.jman.utils.CacheUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/3/9
 */
@ApiOperation("菜单接口")
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping(value = {"/index.html"}, produces = "text/html")
    public ModelAndView menuIndex() {
        return new ModelAndView("page/sys/menu/index.html");
    }

    @GetMapping(value = {"/edit.html"}, produces = "text/html")
    public ModelAndView menuEdit(@RequestParam("id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu", sysMenuService.queryMenuById(id));
        mv.setViewName("page/sys/menu/edit.html");
        return mv;
    }

    @GetMapping(value = {"/add.html"}, produces = "text/html")
    public ModelAndView menuAdd() {
        return new ModelAndView("page/sys/menu/add.html");
    }

    @GetMapping("/query")
    @ApiOperation("查询菜单")
    public List<MenuVo> mean() {
        return new ArrayList<>();
    }

    @PostMapping("/new")
    @ApiOperation("新增菜单")
    public R<String> add(MenuVo menuVo) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuVo, menu);
        sysMenuService.save(menu);
        return R.ok("操作成功");
    }

    @PostMapping("/edit")
    @ApiOperation("修改菜单")
    public R<String> edit(MenuVo menuVo) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(menuVo, menu);
        sysMenuService.updateById(menu);
        return R.ok("操作成功");
    }

    @PostMapping("/del")
    @ApiOperation("删除菜单")
    public R<String> del(@RequestParam("id") int id) {
        sysMenuService.deleteById(id);
        return R.ok("操作成功");
    }

    @GetMapping("/load")
    @ApiOperation("加载菜单")
    // 菜单管理，显示菜单数据，就先不考虑分页
    public R<List<MenuVo>> load() {
        return R.ok(sysMenuService.queryAllMenus());
    }


    @GetMapping("/getTrees")
    @ApiOperation("加载菜单")
    //新增和修改页面 选择菜单
    public List<TreeBeanVo> getTrees() {
        return sysMenuService.queryTreeMenus();
    }
}
