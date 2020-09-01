package com.jicg.jman.config.interceptors;

import cn.hutool.core.util.StrUtil;
import com.jicg.jman.bean.vo.dtree.DTreeResponse;
import com.jicg.jman.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author jicg on 2020/8/4
 */
@Slf4j
public class ResultInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            boolean isDtree = method.getReturnType().equals(DTreeResponse.class);
            if (isDtree) request.setAttribute(DTreeResponse.DTREE_RESP, isDtree);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String comb = StrUtil.nullToDefault(request.getParameter("comb"), "page");
            modelAndView.addObject("webUser", Utils.getUser());
            modelAndView.addObject("comb", comb);
        }
//        if (handler instanceof HandlerMethod) {
//            log.info("ResultInterceptor postHandle HandlerMethod ");
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
