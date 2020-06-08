package com.jicg.jman.config.security.verify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicg.jman.bean.vo.R;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jicg on 2020/4/23
 */

public class VerifyCodeFailureHandleImpl implements IVerifyCodeFailureHandle {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onFailure(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          Exception e) throws
            IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(
                httpServletResponse.getWriter(),
                R.fail("登陆失败：" + e.getLocalizedMessage())
        );
    }
}
