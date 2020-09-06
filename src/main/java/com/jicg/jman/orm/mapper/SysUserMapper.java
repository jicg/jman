package com.jicg.jman.orm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.RespList;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.config.mybaits_plus.MyBaseMapper;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysRole;
import com.jicg.jman.orm.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jicg on 2020/4/16
 */
@Component
public interface SysUserMapper extends MyBaseMapper<SysUser> {
    IPage<UserVo> selectPageVo(Page<?> page);

    List<SysRole> queryRolesByUserId(@Param("userId") long userId);
//    SysUser loadUserByUsername(String username);
}
