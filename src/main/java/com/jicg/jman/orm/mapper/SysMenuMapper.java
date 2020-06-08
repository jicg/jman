package com.jicg.jman.orm.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.jicg.jman.config.mybaits_plus.MyBaseMapper;
import com.jicg.jman.orm.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jicg on 2020/4/16
 */
@Component
public interface SysMenuMapper extends MyBaseMapper<SysMenu> {


    @Select("\"SELECT count(1) \n" +
            "    from sys_menu m,sys_role_menu mr,sys_role r,sys_user_role ur,sys_user u\n" +
            "    where m.id = mr.menu_id\n" +
            "    and m.status = 1\n" +
            "    and mr.role_id = r.id\n" +
            "    and r.id = ur.role_id\n" +
            "    and u.id = ur.user_id \n" +
            "    and u.id = :userid\n" +
            "    and m.perm = :perm")
    int queryCntPerm(@Param("userid") Long userid, @Param("perm") String perm);

    @Select("SELECT distinct m.*\n" +
            "    from sys_menu m,sys_role_menu mr,sys_role r,sys_user_role ur,sys_user u\n" +
            "    where m.id = mr.menu_id\n" +
            "    and m.status = 1\n" +
            "    and mr.role_id = r.id\n" +
            "    and r.id = ur.role_id\n" +
            "    and u.id = ur.user_id \n" +
            "    and u.id = :userid\n" +
            "    and m.pid = :menuid")
    List<SysMenu> queryChildPermsByMeanId(@Param("userid") Long userid, @Param("menuid") Long menuid);


    @Select("SELECT distinct m.*\n" +
            "    from sys_menu m,sys_role_menu mr,sys_role r,sys_user_role ur,sys_user u\n" +
            "    where m.id = mr.menu_id\n" +
            "    and m.status = 1 and m.action_type = 1  \n" +
            "    and mr.role_id = r.id\n" +
            "    and r.id = ur.role_id\n" +
            "    and u.id = ur.user_id \n" +
            "    and u.id = :userid\n" +
            "    and m.pid = :pid")
    List<SysMenu> queryChildMeanByPId(@Param("userid") Long userid, @Param("pid") Long pid);

//    @Select("select * from sys_menu m where m.pid = :pid and m.action_type = 1 and m.status = 1")
//    List<SysMenu> queryMenusByPid(@Param("pid") Long pid);

    @Select("SELECT distinct m.*\n" +
            "    from sys_menu m,sys_role_menu mr,sys_role r,sys_user_role ur,sys_user u\n" +
            "    where m.id = mr.menu_id\n" +
            "    and m.status = 1\n" +
            "    and mr.role_id = r.id\n" +
            "    and r.id = ur.role_id\n" +
            "    and u.id = ur.user_id \n" +
            "    and u.id = :id order by m.sort\n")
    List<SysMenu> queryMenusByUserId(@Param("id") Long id);
}
