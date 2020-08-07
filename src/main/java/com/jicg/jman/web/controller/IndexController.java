package com.jicg.jman.web.controller;

import com.jicg.jman.bean.vo.MenuVo;
import com.jicg.jman.bean.vo.TreeBeanVo;
import com.jicg.jman.config.security.UserDetailsImpl;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysMenuService;
import com.jicg.jman.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jicg on 2020/6/8
 */
@RestController
@RequestMapping("/web")
@Slf4j
public class IndexController {
    @Autowired
    ISysMenuService sysMenuService;

    @GetMapping("/init")
    public Map<String, Object> getInitData() {
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

        List<MenuVo> menuInfo = CacheUtils.get(CacheUtils.MENU_DATA);
        if (menuInfo == null) {
            menuInfo = sysMenuService.queryAllMenus(user.getUser());
            CacheUtils.put(CacheUtils.MENU_DATA, menuInfo);
        }
        datas.put("menuInfo", menuInfo);
        return datas;
    }
}
