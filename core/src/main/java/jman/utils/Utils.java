package com.jicg.jman.utils;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ArrayUtil;
import com.jicg.jman.config.security.UserDetailsImpl;
import com.jicg.jman.orm.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jicg on 2020/5/5
 */
@Slf4j
public class Utils {

    public static boolean isJsonReq(HttpServletRequest req) {
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        log.info(req.getRequestURI() + " " + contentTypeHeader + " " + acceptHeader + " " + xRequestedWith);
        if (acceptHeader != null && acceptHeader.contains("text/html")) return false;
        return !StringUtils.endsWithIgnoreCase(req.getRequestURI(), ".html") && (
                (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                        || (acceptHeader != null && acceptHeader.contains("application/json"))
                        || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        );
    }

    public static SysUser getUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) return null;
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    public static boolean isJsonReq(SavedRequest req) {
        List<String> acceptHeaders = req.getHeaderValues("Accept");
        String acceptHeader = null;
        if (acceptHeaders != null && acceptHeaders.size() > 0) {
            acceptHeader = ArrayUtil.join(acceptHeaders.toArray(), ",").toLowerCase();
        }
        if (acceptHeader != null && acceptHeader.contains("text/html")) return false;

        List<String> contentTypeHeaders = req.getHeaderValues("Content-Type");
        List<String> xRequestedWiths = req.getHeaderValues("X-Requested-With");
        String contentTypeHeader = null;
        if (contentTypeHeaders != null && contentTypeHeaders.size() > 0) {
            contentTypeHeader = ArrayUtil.join(contentTypeHeaders, ",").toLowerCase();
        }
        String xRequestedWith = null;
        if (xRequestedWiths != null && xRequestedWiths.size() > 0) {
            xRequestedWith = xRequestedWiths.get(0);
        }


        log.info(req.getRedirectUrl() + " " + contentTypeHeader + " " + acceptHeader + " " + xRequestedWith);
        return !StringUtils.endsWithIgnoreCase(req.getRedirectUrl(), ".html") && (
                (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                        || (acceptHeader != null && acceptHeader.contains("application/json"))
                        || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        );
    }

    public static String findSqlMsg(String message) {
        if (message != null && message.matches("[^*]+SQLException[^*]+ORA-[0-9]*:[^*]*")) {
//            String pattern = "ORA-[0-9]+:(.*)\nORA-[0-9]+:";
            String pattern = "ORA-[0-9]+:(.*)\n";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(message);
            if (m.find()) {
                return m.group().replaceAll("ORA-[0-9]+:", "").trim();
            }
        }
        if (message != null && message.contains("\n###")) {

        }
        return message;
    }

    public static QuartzJobBean getClass(String jobClassName) throws ClassNotFoundException {
        return (QuartzJobBean) Singleton.get(Class.forName(jobClassName));
    }
}
