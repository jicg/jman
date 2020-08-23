package com.jicg.jman.web.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/user")
public class UserController {

    private final ISysUserService sysUserService;

    public UserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView index() {
        return new ModelAndView("page/sys/auth/user/index.html");
    }

    @GetMapping("query")
    @ApiOperation("查询用户")
    public List<SysUser> query() {
        return new ArrayList<>();
    }

    @GetMapping("list")
    @ApiOperation("查询用户")
    public RespList<UserVo> query(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        return RespList.ok(sysUserService.selectPageVo(new Page<>(page, limit)));
    }
}
