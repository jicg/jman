package com.jicg.jman.orm.mapper;

import com.jicg.jman.config.mybaits_plus.MyBaseMapper;
import com.jicg.jman.orm.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jicg on 2020/4/16
 */
@Component
public interface SysMenuMapper extends MyBaseMapper<SysMenu> {

    int queryCntPerm(@Param("userid") Long userid, @Param("perm") String perm);

    List<SysMenu> queryChildPermsByMeanId(@Param("userid") Long userid, @Param("menuid") Long menuid);


    List<SysMenu> queryChildMeanByPId(@Param("userid") Long userid, @Param("pid") Long pid);

//    @Select("select * from sys_menu m where m.pid = :pid and m.action_type = 1 and m.status = 1")
//    List<SysMenu> queryMenusByPid(@Param("pid") Long pid);

    List<SysMenu> queryMenusByUserId(@Param("id") Long id);
}
