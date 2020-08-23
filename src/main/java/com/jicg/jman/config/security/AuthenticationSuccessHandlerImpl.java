package com.jicg.jman.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicg.jman.utils.Utils;
import com.jicg.jman.bean.vo.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        if (Utils.isJsonReq(request)) {
            response.setContentType("application/json;charset=utf-8");
            objectMapper.writeValue(response.getOutputStream(), Resp.ok("登陆成功"));
        } else {
            log.info("AuthenticationSuccessHandler: "+request.getRequestURI());
            redirectStrategy.sendRedirect(request,
                    response,
                    "/index.html");
        }
    }
}
