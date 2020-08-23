package com.jicg.jman.orm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jicg.jman.bean.vo.UserVo;
import com.jicg.jman.config.mybaits_plus.MyBaseMapper;
import com.jicg.jman.orm.entity.SysMenu;
import com.jicg.jman.orm.entity.SysUser;
import org.springframework.stereotype.Component;

/**
 * @author jicg on 2020/4/16
 */
@Component
public interface SysUserMapper extends MyBaseMapper<SysUser> {
    IPage<UserVo> selectPageVo(Page<?> page);
//    SysUser loadUserByUsername(String username);
}
