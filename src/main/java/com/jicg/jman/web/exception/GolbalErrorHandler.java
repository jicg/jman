package com.jicg.jman.web.exception;

import com.jicg.jman.web.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jicg on 2020/4/16
 */
@Slf4j
@RestControllerAdvice
public class GolbalErrorHandler {
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Object handleException(HttpServletRequest req, Exception e) throws Exception {
        return getReturnData(req, e);

    }

    private Object getReturnData(HttpServletRequest req, Throwable e) {
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if (!StringUtils.endsWithIgnoreCase(req.getRequestURI(), ".html")) {
            if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                    || (acceptHeader != null && acceptHeader.contains("application/json"))
                    || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
                return R.fail(e.getLocalizedMessage());
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("stackTrace", e.getStackTrace());
        modelAndView.setViewName("error/500");
        return modelAndView;

    }

}
