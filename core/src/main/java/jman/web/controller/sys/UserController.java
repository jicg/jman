package com.jicg.jman.web.controller.sys;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.Resp;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.bean.vo.dtree.DTreeResponse;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysRoleService;
import com.jicg.jman.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jicg on 2020/8/5
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final ISysUserService sysUserService;
    private final ISysRoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserController(ISysUserService sysUserService, ISysRoleService roleService, PasswordEncoder passwordEncoder) {
        this.sysUserService = sysUserService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView index() {
        return new ModelAndView("page/sys/auth/user/index.html");
    }


    @GetMapping(path = {"/roles.html"}, produces = {"text/html"})
    public ModelAndView roles(@RequestParam(value = "id") long id) {
        SysUser user = sysUserService.getById(id);
        ModelAndView view = new ModelAndView("page/sys/auth/user/roles.html");
        view.addObject("user", user);
        return view;
    }


    @GetMapping(path = {"/add.html", "/edit.html"}, produces = {"text/html"})
    public ModelAndView edit(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        SysUser user = sysUserService.getById(id);
        ModelAndView view = new ModelAndView("page/sys/auth/user/edit.html");
        view.addObject("user", user);
        return view;
    }


    @PostMapping("role/list")
    @ApiOperation("授权的角色")
    public DTreeResponse query(@RequestParam("userId") long userId) {
        return DTreeResponse.toTreeChecked(
                roleService.list(),
                sysUserService.queryRolesByUserId(userId), (d) -> new DTreeResponse.TreeItem<>(d.getId(), 0L, d.getName()));
//        return Resp.ok("success", sysUserService.queryRolesByUserId(userId));
    }


    @PostMapping("role/save")
    @ApiOperation("授权的角色")
    public Resp<String> query(@RequestParam("userId") long userId
            , @RequestParam(value = "ids[]", required = false) List<String> ids) {
        if (ids == null) ids = new ArrayList<>();
        List<Long> roleIds = ids.stream()
                .map(NumberUtil::parseLong)
                .collect(Collectors.toList());
        sysUserService.saveUserRoles(userId, roleIds);
        return Resp.ok("操作成功");
    }

    @GetMapping("list")
    @ApiOperation("查询用户")
    public RespList<UserVo> query(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        return RespList.ok(sysUserService.selectPageVo(new Page<>(page, limit)));
    }

    @PostMapping("/edit")
    @ApiOperation("修改用户")
    public Resp<String> edit(SysUser user) {
        if (StrUtil.isEmpty(user.getPassword()))
            user.setPassword(passwordEncoder.encode("123456"));
        sysUserService.saveOrUpdate(user);
        return Resp.ok("操作成功");
    }

    @PostMapping("/del")
    @ApiOperation("删除用户")
    public Resp<String> del(@RequestParam("id") long id) {
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser == null) throw new RuntimeException("用户已不存在");
        if (sysUser.getUsername().equals("root")) throw new RuntimeException("不允许删除root！");
        sysUserService.removeById(id);
        return Resp.ok("操作成功");
    }
}
