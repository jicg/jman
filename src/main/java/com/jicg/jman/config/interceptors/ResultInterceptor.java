package com.jicg.jman.config.interceptors;

import cn.hutool.core.util.StrUtil;
import com.jicg.jman.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jicg on 2020/8/4
 */
@Slf4j
public class ResultInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {

//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            UserAuth userAuth = method.getAnnotation(UserAuth.class);
//            if (Objects.nonNull(userAuth)) {
//                SysUser sysUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//                //userAuthenticate.permission()取出permission判断是否需要校验权限
////            if (userId == null || (userAuthenticate.permission() && !checkAuth(userId,request.getRequestURI()))){
////                throw new FastRuntimeException(20001,"No access");
////            }
//                request.setAttribute(Const.USER_KEY, sysUser);
//            }
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
        if (handler instanceof HandlerMethod) {
            log.info("ResultInterceptor postHandle HandlerMethod ");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
