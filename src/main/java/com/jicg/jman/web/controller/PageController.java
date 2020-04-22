package com.jicg.jman.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jicg on 2020/3/14
 */
@Slf4j
@Controller
public class PageController {
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
        log.info(name);
        if (true)
            throw new RuntimeException("sadfasdf");
        return "/page/" + name;
    }
}
