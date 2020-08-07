package com.jicg.jman.web.controller;

import com.jicg.jman.bean.vo.R;
import com.jicg.jman.config.AppConfig;
import com.jicg.jman.config.interceptors.annos.ParamUser;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jicg on 2020/6/8
 */
@RestController
@RequestMapping("/web")
@Slf4j
public class IndexController {
    private final ISysMenuService sysMenuService;
    private final AppConfig appConfig;

    @Autowired
    public IndexController(ISysMenuService sysMenuService, AppConfig appConfig) {
        this.sysMenuService = sysMenuService;
        this.appConfig = appConfig;
    }

    @GetMapping("/init")
    public Map<String, Object> getInitData(@ParamUser SysUser user) {
        Map<String, Object> datas = new HashMap<>();
        datas.put("homeInfo", new HashMap<String, String>() {{
            put("title", appConfig.getTitle());
            put("href", appConfig.getIndexPage());
        }});
        datas.put("logoInfo", new HashMap<String, String>() {{
            put("title", appConfig.getTitle());
            put("image", appConfig.getIcon());
        }});
        datas.put("menuInfo", sysMenuService.queryAllMenus(user));
        return datas;
    }

    @GetMapping("/clearCache")
    public R clearCache(@ParamUser SysUser user) {
        sysMenuService.clearCache(user);
        return R.ok("操作成功");
    }
}
