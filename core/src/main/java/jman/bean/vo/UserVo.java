package com.jicg.jman.bean.vo;

import lombok.Data;

/**
 * @author jicg on 2020/8/23
 */
@Data
public class UserVo {
    private long id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private String remark;
}
