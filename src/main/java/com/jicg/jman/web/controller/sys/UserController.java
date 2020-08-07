package com.jicg.jman.web.controller.sys;

import com.jicg.jman.orm.entity.SysUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView index() {
        return new ModelAndView("page/sys/user/index.html");
    }

    @GetMapping("query")
    @ApiOperation("查询用户")
    public List<SysUser> query() {
        return new ArrayList<>();
    }
}
