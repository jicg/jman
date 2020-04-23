package com.jicg.jman.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicg.jman.web.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jicg on 2020/4/20
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws
            IOException, ServletException {
        log.error(e.getLocalizedMessage(), e);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(
                httpServletResponse.getWriter(),
                R.fail("登陆失败：用户或密码错误 " + e.getLocalizedMessage())
        );
    }
}
