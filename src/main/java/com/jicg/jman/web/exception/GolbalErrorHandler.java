package com.jicg.jman.web.exception;

import com.jicg.jman.utils.Utils;
import com.jicg.jman.bean.vo.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jicg on 2020/4/16
 */
@Slf4j
@ControllerAdvice
public class GolbalErrorHandler {
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Object handleException(HttpServletRequest req, Exception e) throws Exception {
        return getReturnData(req, e);

    }

//    @ExceptionHandler(value = Throwable.class)
//    public Object handleException2(HttpServletRequest req, Exception e) throws Exception {
//        return getReturnData(req, e);
//
//    }

    private Object getReturnData(HttpServletRequest req, Throwable e) {
        log.info("---------------------------");
        if (Utils.isJsonReq(req)) {
            return Resp.fail(e.getLocalizedMessage());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("stackTrace", e.getStackTrace());
        modelAndView.setViewName("error/500");
        log.error(e.getLocalizedMessage(), e);
        return modelAndView;

    }

}
