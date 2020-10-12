package com.jicg.jman.web.controller.sys;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.jicg.jman.bean.vo.Resp;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.config.interceptors.annos.ParamUser;
import com.jicg.jman.config.security.verify.IVerifyCodeService;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jicg on 2020/3/14
 */
@Slf4j
@Controller
public class PageController {

    final IVerifyCodeService verifyCodeService;
    private final ISysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;

    public PageController(IVerifyCodeService verifyCodeService, ISysUserService sysUserService, PasswordEncoder passwordEncoder) {
        this.verifyCodeService = verifyCodeService;
        this.sysUserService = sysUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/index.html"}, produces = "text/html")
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/login.html"}, produces = "text/html")
    public String login() {
        return "login";
    }


    @GetMapping(value = {"/page/{mod}/{name}.html", "/page/{name}.html"}, produces = "text/html")
    public ModelAndView index(@PathVariable("name") String name,
                              @PathVariable(value = "mod", required = false) String mod) {
        ModelAndView mv = new ModelAndView();
        String modd = "";
        if (!StrUtil.isEmpty(mod)) modd = StrUtil.appendIfMissing(mod, "/");
        mv.setViewName("/page/" + modd + name);
        return mv;
    }

    @GetMapping(value = "/verify")
    @ResponseBody
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        verifyCodeService.verifyResp(request, response);
    }

    @GetMapping(value = {"/user/setting.html"}, produces = "text/html")
    public String viewSetting() {
//        ModelAndView view = new ModelAndView("page/sys/user/setting.html");
        return "page/sys/user/setting.html";
    }


    @GetMapping(value = {"/changePassword.html"}, produces = "text/html")
    public String viewChangePwd() {
//        ModelAndView view = new ModelAndView("page/sys/user/changePassword.html");
        return "page/sys/user/changePassword.html";
    }


    @PostMapping("/changePwd")
    @ResponseBody
    public Resp<String> changePwd(@ParamUser SysUser sysUser, String old_password, String new_password, String again_password) {
        if (!StrUtil.equalsIgnoreCase(new_password, again_password)) throw new RuntimeException("两次密码不一致！");
        if (StrUtil.isEmpty(new_password)) throw new RuntimeException("密码不能为空！");
        if (StrUtil.length(new_password) < 6) throw new RuntimeException("密码不能小于6位数");
        if (!passwordEncoder.matches(old_password, sysUser.getPassword())) throw new RuntimeException("密码不对！");
        sysUser.setPassword(passwordEncoder.encode(new_password));
        sysUserService.saveOrUpdate(sysUser);
        return Resp.ok("操作成功");
    }

    @PostMapping("/changeUserInfo")
    @ResponseBody
    public Resp<String> changeUserInfo(@ParamUser SysUser sysUser,
                                       UserVo user) {
        BeanUtil.copyProperties(user, sysUser, "id", "username", "password");
        sysUserService.saveOrUpdate(sysUser);
        return Resp.ok("操作成功");
    }
}
