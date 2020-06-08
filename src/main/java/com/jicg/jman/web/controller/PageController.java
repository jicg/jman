package com.jicg.jman.web.controller;

import com.jicg.jman.config.security.verify.IVerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jicg on 2020/3/14
 */
@Slf4j
@Controller
public class PageController {

    @Autowired
    IVerifyCodeService verifyCodeService;

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

    @GetMapping(value = "/page/{name}.html", produces = "text/html")
    public String index(@PathVariable("name") String name) {
        return "/page/" + name;
    }

    @GetMapping(value = "/verify")
    @ResponseBody
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        verifyCodeService.verifyResp(request, response);
    }
}
