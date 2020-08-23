package com.jicg.jman.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicg.jman.bean.vo.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jicg on 2020/4/20
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(httpServletResponse.getOutputStream(), Resp.ok("操作成功"));
    }
}
