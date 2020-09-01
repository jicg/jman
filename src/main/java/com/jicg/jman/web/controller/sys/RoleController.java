package com.jicg.jman.web.controller.sys;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.Resp;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.dtree.DTreeReq;
import com.jicg.jman.bean.vo.dtree.DTreeResponse;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.service.ISysMenuService;
import com.jicg.jman.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/8/5
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    private final ISysRoleService sysRoleService;
    private final ISysMenuService sysMenuService;

    public RoleController(ISysRoleService sysRoleService, ISysMenuService sysMenuService) {
        this.sysRoleService = sysRoleService;
        this.sysMenuService = sysMenuService;
    }

    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView indexPage() {
        return new ModelAndView("page/sys/auth/role/index.html");
    }

    @GetMapping(path = {"/add.html"}, produces = {"text/html"})
    public ModelAndView addPage() {
        return new ModelAndView("page/sys/auth/role/edit.html");
    }

    @GetMapping(path = {"/edit.html"}, produces = {"text/html"})
    public ModelAndView editPage(@RequestParam("id") long id) {
        ModelAndView view = new ModelAndView("page/sys/auth/role/edit.html");
        SysRole role = sysRoleService.getById(id);
        view.addObject("role", role);
        return view;
    }

    @GetMapping(path = {"/auth.html"}, produces = {"text/html"})
    public ModelAndView auth(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        SysRole role = sysRoleService.getById(id);
        ModelAndView view = new ModelAndView("page/sys/auth/role/auth.html");
        view.addObject("role", role);
        return view;
    }

    @GetMapping("list")
    @ApiOperation("查询用户")
    public RespList<SysRole> query(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        return RespList.ok(sysRoleService.page(new Page<>(page, limit)));
    }


    @PostMapping("/edit")
    @ApiOperation("修改用户")
    public Resp<String> edit(SysRole role) {
        sysRoleService.saveOrUpdate(role);
        return Resp.ok("操作成功");
    }

    @PostMapping("/del")
    @ApiOperation("删除用户")
    public Resp<String> del(@RequestParam("id") long id) {
        SysRole sysRole = sysRoleService.getById(id);
        if (sysRole == null) throw new RuntimeException("用户已不存在");
        sysRoleService.removeById(id);
        return Resp.ok("操作成功");
    }

    @PostMapping("/auth/data")
    public DTreeResponse json() {
        List<SysMenu> sysMenuList = sysMenuService.list();
        return DTreeResponse.toTreeChecked(sysMenuList, sysMenuList);
    }

    @PostMapping("/auth/save")
    public Resp<String> save(@RequestParam(value = "data") String data) {
        log.error("save  " + data);
        List<DTreeReq> reqs = JSONObject.parseArray(data, DTreeReq.class);
        log.error("save  " + JSONObject.toJSONString(reqs));
        return Resp.ok("操作成功");
    }

}
