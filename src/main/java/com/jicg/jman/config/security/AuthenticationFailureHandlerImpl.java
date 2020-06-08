package com.jicg.jman.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicg.jman.utils.Utils;
import com.jicg.jman.bean.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jicg on 2020/4/20
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws
            IOException, ServletException {
        if (Utils.isJsonReq(httpServletRequest)) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(
                    httpServletResponse.getWriter(),
                    R.fail("登陆失败：用户或密码错误 " + e.getLocalizedMessage())
            );
        }else{
            log.info("AuthenticationSuccessHandler: "+httpServletRequest.getRequestURI());
            redirectStrategy.sendRedirect(httpServletRequest,
                    httpServletResponse,
                    "/login.html");
        }
    }
}
