package com.jicg.jman.config.security.verify;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author jicg on 2020/4/23
 */
@Data
public class VerifyCodeBean implements Serializable {
    public static final String VERIFY_CODE_SESSION_KEY = "VERIFY_CODE_SESSION_KEY";
    private String code;
    private LocalDateTime expireTime;

    public VerifyCodeBean(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    //判断验证码是否过期
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}