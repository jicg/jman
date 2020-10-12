package com.jicg.jman.config.security;

import cn.hutool.core.util.StrUtil;
import com.jicg.jman.config.security.verify.IVerifyCodeFailureHandle;
import com.jicg.jman.config.security.verify.IVerifyCodeService;
import com.jicg.jman.config.security.verify.ValidateCodeException;
import com.jicg.jman.config.security.verify.VerifyCodeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jicg on 2020/4/23
 */
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private IVerifyCodeService verifyCodeService;
    @Autowired
    private IVerifyCodeFailureHandle verifyCodeFailureHandle;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StrUtil.equals("/api/login", request.getRequestURI())
                && StrUtil.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                verifyCodeService.check(request, response);
            } catch (Exception e) {
//                log.error(e.getLocalizedMessage(), e);
                verifyCodeFailureHandle.onFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
