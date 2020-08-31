package com.jicg.jman.web.exception;

import com.jicg.jman.bean.vo.dtree.DTreeResponse;
import com.jicg.jman.bean.vo.dtree.Status;
import com.jicg.jman.utils.Utils;
import com.jicg.jman.bean.vo.Resp;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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
        Object obj = req.getAttribute(DTreeResponse.DTREE_RESP);
        if (obj != null && ((boolean) obj)) {
            DTreeResponse dTreeResponse = new DTreeResponse();
            dTreeResponse.setStatus(new Status(500, "错误: " + e.getLocalizedMessage()));
            return dTreeResponse;
        }

        if (Utils.isJsonReq(req)) {
            if (e instanceof UncategorizedSQLException) {
                return Resp.fail(Utils.findSqlMsg(e.getLocalizedMessage()));
            }
            if (e instanceof DataAccessException) {
                Throwable throwable = ((DataAccessException) e).getRootCause();
                if (throwable == null) {
                    return Resp.fail("数据存储异常：未知错误！");
                }
                return Resp.fail("数据存储异常：" + throwable.getLocalizedMessage());
            }
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
