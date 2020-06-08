package com.jicg.jman.web.controller.web;

import com.jicg.jman.config.security.UserDetailsImpl;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jicg on 2020/6/8
 */
@RestController
@RequestMapping("/web")
public class IndexController {
    @Autowired
    ISysMenuService sysMenuService;

    @GetMapping("/init")
    public Map<String, Object> getInitData() {
//        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> datas = new HashMap<>();
        datas.put("homeInfo", new HashMap<String, String>() {{
            put("title", "测试");
            put("href", "www.baidu.com");
        }});
        datas.put("logoInfo", new HashMap<String, String>() {{
            put("title", "jman");
            put("image", "https://portrait.gitee.com/uploads/avatars/user/149/448289_jicg_1578924211.png!avatar100");
        }});
        datas.put("menuInfo", sysMenuService.queryAllMenus(user.getUser()));
        return datas;
    }
}
