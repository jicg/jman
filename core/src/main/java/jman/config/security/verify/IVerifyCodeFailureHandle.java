package com.jicg.jman.config.security.verify;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jicg on 2020/4/23
 */

public interface IVerifyCodeFailureHandle {
    void onFailure(HttpServletRequest httpServletRequest,
                   HttpServletResponse httpServletResponse,
                   Exception e) throws IOException, ServletException;
}
