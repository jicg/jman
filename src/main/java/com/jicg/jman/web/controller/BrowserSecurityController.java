package com.jicg.jman.web.controller;

import com.jicg.jman.web.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author jicg on 2020/4/20
 */
@Slf4j
@RestController
public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 当需要身份认证时跳转到这里
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/api/login_p")
    public R requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            String[] dt = savedRequest.getParameterValues("dt");
            log.info("引发跳转的请求是:" + target + "  " + Arrays.toString(dt));
            if (dt != null && dt.length > 0) {
                if ("html".equalsIgnoreCase(dt[0])) {
                    redirectStrategy.sendRedirect(
                            request,
                            response,
                            "/login.html"
                    );
                }
            }
            if (StringUtils.endsWithIgnoreCase(target, ".html")||StringUtils.endsWithIgnoreCase(target, "/")) {
                redirectStrategy.sendRedirect(
                        request,
                        response,
                        "/login.html"
                );
            }
        }
        return R.fail("请登陆");
    }
}
