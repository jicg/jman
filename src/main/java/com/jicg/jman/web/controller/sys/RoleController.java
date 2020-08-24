package com.jicg.jman.web.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.Resp;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import com.jicg.jman.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/8/5
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final ISysRoleService sysRoleService;

    public RoleController(ISysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @GetMapping(path = {"/index.html"}, produces = {"text/html"})
    public ModelAndView indexPage() {
        return new ModelAndView("page/sys/auth/role/index.html");
    }

    @GetMapping(path = {"/add.html"}, produces = {"text/html"})
    public ModelAndView addPage() {
        return new ModelAndView("page/sys/auth/role/edit.html");
    }

    @GetMapping(path = {"/edit.html"}, produces = {"text/html"})
    public ModelAndView editPage(@RequestParam("id") long id) {
        ModelAndView view = new ModelAndView("page/sys/auth/role/edit.html");
        SysRole role = sysRoleService.getById(id);
        view.addObject("role", role);
        return view;
    }

    @GetMapping(path = {"/auth.html"}, produces = {"text/html"})
    public ModelAndView auth(@RequestParam(value = "id", required = false, defaultValue = "0") long id) {
        SysRole role = sysRoleService.getById(id);
        ModelAndView view = new ModelAndView("page/sys/auth/role/auth.html");
        view.addObject("role", role);
        return view;
    }

    @GetMapping("list")
    @ApiOperation("查询用户")
    public RespList<SysRole> query(@RequestParam("page") long page, @RequestParam("limit") long limit) {
        return RespList.ok(sysRoleService.page(new Page<>(page, limit)));
    }


    @PostMapping("/edit")
    @ApiOperation("修改用户")
    public Resp<String> edit(SysRole role) {
        sysRoleService.saveOrUpdate(role);
        return Resp.ok("操作成功");
    }

    @PostMapping("/del")
    @ApiOperation("删除用户")
    public Resp<String> del(@RequestParam("id") long id) {
        SysRole sysRole = sysRoleService.getById(id);
        if (sysRole == null) throw new RuntimeException("用户已不存在");
        sysRoleService.removeById(id);
        return Resp.ok("操作成功");
    }

    @PostMapping("/auth/data")
    public String json() {
        return "{\n" +
                "\t\"status\":{\"code\":200,\"message\":\"操作成功\"},\n" +
                "\t\"data\":[{\n" +
                "\t\t\"id\":\"001\",\n" +
                "\t\t\"title\": \"湖南省\",\n" +
                "\t\t\"last\": false,\n" +
                "\t\t\"level\": \"1\",\n" +
                "\t\t\"parentId\": \"-1\",\n" +
                "\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":\"001001\",\n" +
                "\t\t\t\"title\": \"长沙市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"001\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"001002\",\n" +
                "\t\t\t\"title\": \"株洲市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"001\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"001003\",\n" +
                "\t\t\t\"title\": \"湘潭市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"001\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"001004\",\n" +
                "\t\t\t\"title\": \"衡阳市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"001\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"001005\",\n" +
                "\t\t\t\"title\": \"郴州市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"001\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":\"002\",\n" +
                "\t\t\"title\": \"湖北省\",\n" +
                "\t\t\"last\": false,\n" +
                "\t\t\"parentId\": \"-1\",\n" +
                "\t\t\"level\": \"1\",\n" +
                "\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":\"002001\",\n" +
                "\t\t\t\"title\": \"武汉市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"002\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"002002\",\n" +
                "\t\t\t\"title\": \"黄冈市\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"002\",\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"002003\",\n" +
                "\t\t\t\"title\": \"潜江市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"002\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"002004\",\n" +
                "\t\t\t\"title\": \"荆州市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"002\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"002005\",\n" +
                "\t\t\t\"title\": \"襄阳市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"002\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\t\t\t\t\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":\"003\",\n" +
                "\t\t\"title\": \"广东省\",\n" +
                "\t\t\"last\": false,\n" +
                "\t\t\"parentId\": \"-1\",\n" +
                "\t\t\"level\": \"1\",\n" +
                "\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":\"003001\",\n" +
                "\t\t\t\"title\": \"广州市\",\n" +
                "\t\t\t\"last\":false,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"level\": \"2\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"children\":[{\n" +
                "\t\t\t\t\"id\":\"003001001\",\n" +
                "\t\t\t\t\"title\": \"天河区\",\n" +
                "\t\t\t\t\"last\":true,\n" +
                "\t\t\t\t\"parentId\": \"003001\",\n" +
                "\t\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\t\"level\": \"3\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"id\":\"003001002\",\n" +
                "\t\t\t\t\"title\": \"花都区\",\n" +
                "\t\t\t\t\"last\":true,\n" +
                "\t\t\t\t\"parentId\": \"003001\",\n" +
                "\t\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\t\"level\": \"3\"\n" +
                "\t\t\t}]\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"003002\",\n" +
                "\t\t\t\"title\": \"深圳市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"003003\",\n" +
                "\t\t\t\"title\": \"中山市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"003004\",\n" +
                "\t\t\t\"title\": \"东莞市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"003005\",\n" +
                "\t\t\t\"title\": \"珠海市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"003006\",\n" +
                "\t\t\t\"title\": \"韶关市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"003\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":\"004\",\n" +
                "\t\t\"title\": \"浙江省\",\n" +
                "\t\t\"last\": false,\n" +
                "\t\t\"level\": \"1\",\n" +
                "\t\t\"parentId\": \"-1\",\n" +
                "\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":\"004001\",\n" +
                "\t\t\t\"title\": \"杭州市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"004\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"004002\",\n" +
                "\t\t\t\"title\": \"温州市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"004\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"004003\",\n" +
                "\t\t\t\"title\": \"绍兴市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"004\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"004004\",\n" +
                "\t\t\t\"title\": \"金华市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"004\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"id\":\"004005\",\n" +
                "\t\t\t\"title\": \"义乌市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"004\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t}]\n" +
                "\t},{\n" +
                "\t\t\"id\":\"005\",\n" +
                "\t\t\"title\": \"福建省\",\n" +
                "\t\t\"last\": false,\n" +
                "\t\t\"parentId\": \"-1\",\n" +
                "\t\t\"level\": \"1\",\n" +
                "\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\"children\":[{\n" +
                "\t\t\t\"id\":\"005001\",\n" +
                "\t\t\t\"title\": \"厦门市\",\n" +
                "\t\t\t\"last\":true,\n" +
                "\t\t\t\"parentId\": \"005\",\n" +
                "\t\t\t\"checkArr\": [{\"type\": \"0\", \"checked\": \"0\"}],\n" +
                "\t\t\t\"level\": \"2\"\n" +
                "\t\t}]\n" +
                "\t}]\n" +
                "}";
    }
}
