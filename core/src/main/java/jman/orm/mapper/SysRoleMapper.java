package com.jicg.jman.orm.mapper;

import com.jicg.jman.config.mybaits_plus.MyBaseMapper;
import com.jicg.jman.orm.entity.SysRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jicg on 2020/8/14
 */
@Component
public interface SysRoleMapper extends MyBaseMapper<SysRole> {

    List<Long> queryRolesByUserId(long userId);

    void insertMenuIdByRoleId(long userId, List<Long> roleIds);

    void deleteMenuIdByRoleId(long userId, List<Long> roleIds);
}
