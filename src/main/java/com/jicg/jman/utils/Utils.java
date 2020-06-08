package com.jicg.jman.utils;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jicg on 2020/5/5
 */
@Slf4j
public class Utils {

    public static boolean isJsonReq(HttpServletRequest req) {
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        return !StringUtils.endsWithIgnoreCase(req.getRequestURI(), ".html") && (
                (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                        || (acceptHeader != null && acceptHeader.contains("application/json"))
                        || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        );
    }

    public static boolean isJsonReq(SavedRequest req) {
        List<String> contentTypeHeaders = req.getHeaderValues("Content-Type");
        List<String> acceptHeaders = req.getHeaderValues("Accept");
        List<String> xRequestedWiths = req.getHeaderValues("X-Requested-With");
        String xRequestedWith = null;
        if (xRequestedWiths != null && xRequestedWiths.size() > 0) {
            xRequestedWith = xRequestedWiths.get(0);
        }

        String contentTypeHeader = null;
        if (contentTypeHeaders != null && contentTypeHeaders.size() > 0) {
            contentTypeHeader = ArrayUtil.join(contentTypeHeaders, ",").toLowerCase();
        }
        String acceptHeader = null;
        if (acceptHeaders != null && acceptHeaders.size() > 0) {
            acceptHeader = ArrayUtil.join(acceptHeaders.toArray(), ",").toLowerCase();
        }
        return !StringUtils.endsWithIgnoreCase(req.getRedirectUrl(), ".html") && (
                (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                        || (acceptHeader != null && acceptHeader.contains("application/json"))
                        || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        );
    }
}
