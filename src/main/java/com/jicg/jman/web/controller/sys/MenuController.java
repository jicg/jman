package com.jicg.jman.web.controller.sys;

import com.jicg.jman.bean.vo.R;
import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.service.impl.SysMenuService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView menuIndex(
            @RequestParam(value = "comb", defaultValue = "page") String comb) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comb", comb);
        mv.setViewName("page/sys/menu/index.html");
        return mv;
    }

    @GetMapping(value = {"/edit.html"}, produces = "text/html")
    public ModelAndView menuEdit(@RequestParam("id") long id,
                                 @RequestParam(value = "comb", defaultValue = "page") String comb) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comb", comb);
        log.error("-----------------------------------------");
        mv.addObject("menu", sysMenuService.queryMenuById(id));
        mv.setViewName("page/sys/menu/edit.html");
        return mv;
    }

    @GetMapping(value = {"/add.html"}, produces = "text/html")
    public ModelAndView menuAdd(
            @RequestParam(value = "comb", defaultValue = "page") String comb) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comb", comb);
        mv.setViewName("page/sys/menu/add.html");
        return mv;
    }

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


    @GetMapping("/getTrees")
    @ApiOperation("新增菜单")
    public List<TreeBeanVo> getTrees() {
        return sysMenuService.queryTreeMenus();
    }
}
