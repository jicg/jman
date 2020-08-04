package com.jicg.jman.web.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.jicg.jman.config.security.verify.IVerifyCodeService;
import lombok.extern.slf4j.Slf4j;
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

    public PageController(IVerifyCodeService verifyCodeService) {
        this.verifyCodeService = verifyCodeService;
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
                              @PathVariable(value = "mod", required = false) String mod,
                              @RequestParam(value = "comb", defaultValue = "page") String comb) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("comb", comb);
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
}
