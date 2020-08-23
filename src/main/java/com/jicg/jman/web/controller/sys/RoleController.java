package com.jicg.jman.web.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/8/5
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final ISysRoleService sysRoleService;

    public RoleController(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView indexPage() {
        return new ModelAndView("page/sys/auth/user/index.html");
    }

    @GetMapping(path = {"/add.html"}, produces = {"text/html"})
    public ModelAndView addPage() {
        return new ModelAndView("page/sys/auth/user/add.html");
    }

    @GetMapping(path = {"/edit.html"}, produces = {"text/html"})
    public ModelAndView editPage(@RequestParam("id") long id) {
        ModelAndView view = new ModelAndView("page/sys/auth/user/add.html");
        SysRole role = sysRoleService.getById(id);
        view.addObject("role", role);
        return view;
    }

    @GetMapping("list")
    @ApiOperation("查询用户")
    public RespList<SysRole> query(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        return RespList.ok(sysRoleService.page(new Page<>(page, limit)));
    }
}
